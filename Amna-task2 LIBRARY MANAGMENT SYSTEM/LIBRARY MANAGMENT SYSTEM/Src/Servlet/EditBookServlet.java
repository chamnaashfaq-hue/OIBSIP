package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Book;
import DAO.BookDAO;

public class EditBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        String category = request.getParameter("category");
        int totalQuantity = Integer.parseInt(request.getParameter("totalQuantity"));
        int availableQuantity = Integer.parseInt(request.getParameter("availableQuantity"));

        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setCategory(category);
        book.setTotalQuantity(totalQuantity);
        book.setAvailableQuantity(availableQuantity);

        BookDAO bookDAO = new BookDAO();
        boolean success = bookDAO.updateBook(book);

        if (success) {
            out.println("<h2>Book updated successfully!</h2>");
        } else {
            out.println("<h2>Failed to update book.</h2>");
        }
    }
}