package Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Book;
import DAO.BookDAO;

public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.searchBooks(keyword);

        request.setAttribute("books", books);
        request.getRequestDispatcher("viewbooks.jsp").forward(request, response);
    }
}