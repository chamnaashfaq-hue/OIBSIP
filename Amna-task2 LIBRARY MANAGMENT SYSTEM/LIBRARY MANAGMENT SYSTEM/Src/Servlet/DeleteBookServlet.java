package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.BookDAO;

public class DeleteBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        BookDAO bookDAO = new BookDAO();
        boolean success = bookDAO.deleteBook(id);

        if (success) {
            out.println("<h2>Book deleted successfully!</h2>");
        } else {
            out.println("<h2>Failed to delete book.</h2>");
        }
    }
}