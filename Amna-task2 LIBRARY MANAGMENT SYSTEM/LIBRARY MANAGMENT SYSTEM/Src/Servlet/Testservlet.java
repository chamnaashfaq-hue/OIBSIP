package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import java.sql.Connection;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.DBConnection;
 public class TestServlet extends HttpServlet {
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            out.println("<h1>Database connection successful!</h1>");
        } else {
            out.println("<h1>Failed to connect to the database.</h1>");
        }
    }
    
 }
