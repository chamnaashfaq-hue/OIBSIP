package DAO;

import Model.IssuedBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class IssuedBookDAO {

    public boolean issueBook(int bookId, int userId) {
        String sql = "INSERT INTO issued_books (book_id, user_id, issue_date, due_date, status) VALUES (?, ?, ?, ?, 'issued')";

        Date issueDate = new Date(System.currentTimeMillis());
        Date dueDate = new Date(System.currentTimeMillis() + (14L * 24 * 60 * 60 * 1000));

        BookDAO bookDAO = new BookDAO();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            stmt.setInt(2, userId);
            stmt.setDate(3, issueDate);
            stmt.setDate(4, dueDate);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                bookDAO.updateAvailableQuantity(bookId, -1);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBook(int issuedBookId, int bookId) {
        Date returnDate = new Date(System.currentTimeMillis());
        BookDAO bookDAO = new BookDAO();

        // Pehle due_date nikalo, taake fine calculate ho sake
        String getDueDateSql = "SELECT due_date FROM issued_books WHERE id = ?";
        int fine = 0;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement getStmt = con.prepareStatement(getDueDateSql)) {

            getStmt.setInt(1, issuedBookId);
            ResultSet rs = getStmt.executeQuery();

            if (rs.next()) {
                Date dueDate = rs.getDate("due_date");
                long diffMillis = returnDate.getTime() - dueDate.getTime();
                long daysLate = TimeUnit.MILLISECONDS.toDays(diffMillis);

                if (daysLate > 0) {
                    fine = (int) daysLate * 5; // Rs 5 per din
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String updateSql = "UPDATE issued_books SET return_date = ?, status = 'returned', fine_amount = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(updateSql)) {

            stmt.setDate(1, returnDate);
            stmt.setInt(2, fine);
            stmt.setInt(3, issuedBookId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                bookDAO.updateAvailableQuantity(bookId, 1);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<IssuedBook> getIssuedBooks() {
        List<IssuedBook> list = new ArrayList<>();
        String sql = "SELECT ib.id, ib.book_id, ib.user_id, ib.issue_date, ib.due_date, ib.status, " +
                     "b.title AS book_title, u.name AS user_name " +
                     "FROM issued_books ib " +
                     "JOIN books b ON ib.book_id = b.id " +
                     "JOIN users u ON ib.user_id = u.id " +
                     "WHERE ib.status = 'issued'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                IssuedBook ib = new IssuedBook();
                ib.setId(rs.getInt("id"));
                ib.setBookId(rs.getInt("book_id"));
                ib.setUserId(rs.getInt("user_id"));
                ib.setIssueDate(rs.getDate("issue_date"));
                ib.setDueDate(rs.getDate("due_date"));
                ib.setStatus(rs.getString("status"));
                ib.setBookTitle(rs.getString("book_title"));
                ib.setUserName(rs.getString("user_name"));
                list.add(ib);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Fine wali books ki list (jinka fine baaki hai, pay nahi hua)
    public List<IssuedBook> getUnpaidFines() {
        List<IssuedBook> list = new ArrayList<>();
        String sql = "SELECT ib.id, ib.book_id, ib.user_id, ib.fine_amount, " +
                     "b.title AS book_title, u.name AS user_name " +
                     "FROM issued_books ib " +
                     "JOIN books b ON ib.book_id = b.id " +
                     "JOIN users u ON ib.user_id = u.id " +
                     "WHERE ib.fine_amount > 0 AND ib.status = 'returned'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                IssuedBook ib = new IssuedBook();
                ib.setId(rs.getInt("id"));
                ib.setBookId(rs.getInt("book_id"));
                ib.setUserId(rs.getInt("user_id"));
                ib.setFineAmount(rs.getInt("fine_amount"));
                ib.setBookTitle(rs.getString("book_title"));
                ib.setUserName(rs.getString("user_name"));
                list.add(ib);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Fine paid mark karo (fine_amount ko 0 kar do)
    public boolean markFinePaid(int issuedBookId) {
        String sql = "UPDATE issued_books SET fine_amount = 0 WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, issuedBookId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}