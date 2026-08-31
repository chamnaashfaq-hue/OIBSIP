package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.IssuedBookDAO;

public class MarkFinePaidServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int issuedBookId = Integer.parseInt(request.getParameter("issuedBookId"));

        IssuedBookDAO dao = new IssuedBookDAO();
        boolean success = dao.markFinePaid(issuedBookId);

        if (success) {
            out.println("<h2>Fine marked as paid!</h2>");
        } else {
            out.println("<h2>Failed to update fine.</h2>");
        }
    }
}