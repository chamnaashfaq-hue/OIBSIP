package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.IssuedBookDAO;

public class IssueServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        IssuedBookDAO dao = new IssuedBookDAO();
        boolean success = dao.issueBook(bookId, userId);

        if (success) {
            out.println("<h2>Book issued successfully!</h2>");
        } else {
            out.println("<h2>Failed to issue book.</h2>");
        }
    }
}