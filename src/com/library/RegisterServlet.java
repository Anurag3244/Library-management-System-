package com.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection configuration
    private final String DB_URL = "jdbc:mysql://localhost:3306/library_db"; // Updated database name
    private final String DB_USERNAME = "root"; // Your MySQL username
    private final String DB_PASSWORD = "";     // Your MySQL password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Check if username already exists
            String checkQuery = "SELECT username FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                out.print("<h3>Username already exists. Please try another.</h3>");
            } else {
                // Insert new user
                String insertQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.setString(3, role);

                int rowsInserted = insertStmt.executeUpdate();
                if (rowsInserted > 0) {
                    out.print("<h3>Registration successful! Go to <a href='login.html'>Login</a>.</h3>");
                } else {
                    out.print("<h3>Error during registration. Please try again.</h3>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("<h3>Database connection error.</h3>");
        }
    }
}
