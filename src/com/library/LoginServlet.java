package com.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection configuration
    private final String DB_URL = "jdbc:mysql://localhost:3306/library_db?useSSL=false";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "123";

    @Override
    public void init() throws ServletException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC Driver not found", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Validate username and password
            String query = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                // Store user information in session
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("role", role);
                switch (role) {
                    case "Admin":
                        response.sendRedirect("admin-menu.html");
                        break;
                    case "Librarian":
                        response.sendRedirect("librarian-menu.html");
                        break;
                    case "Member":
                        response.sendRedirect("member-menu.html");
                        break;
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
                dispatcher.forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
            dispatcher.forward(request, response);
        }
    }
}