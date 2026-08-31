package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Reservation_System;user=sa;password=admin123;encrypt=true;trustServerCertificate=true;";
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Error connecting to database:");
            e.printStackTrace();
        }
        return connection;
    }
}