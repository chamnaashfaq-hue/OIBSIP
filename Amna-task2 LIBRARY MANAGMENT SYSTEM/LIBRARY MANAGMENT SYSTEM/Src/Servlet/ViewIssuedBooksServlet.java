package Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.IssuedBook;
import DAO.IssuedBookDAO;

public class ViewIssuedBooksServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IssuedBookDAO dao = new IssuedBookDAO();
        List<IssuedBook> list = dao.getIssuedBooks();

        request.setAttribute("issuedBooks", list);
        request.getRequestDispatcher("issuebooks.jsp").forward(request, response);
    }
}