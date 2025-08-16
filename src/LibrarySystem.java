import java.sql.*;
import java.util.*;

public class LibrarySystem {

    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            connectToDatabase();
            login();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private static void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/library_db";
            String user = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    private static void login() {
        System.out.println("=== Library Management System ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            String query = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                switch (role) {
                    case "Administrator":
                        adminMenu();
                        break;
                    case "Librarian":
                        librarianMenu();
                        break;
                    case "Member":
                        memberMenu();
                        break;
                    default:
                        System.out.println("Invalid role.");
                }
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. View All Members");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    viewAllMembers();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void librarianMenu() {
        while (true) {
            System.out.println("\n=== Librarian Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. Add a Book");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void memberMenu() {
        while (true) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. View Borrowed Books");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    viewBorrowedBooks();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        try {
            String query = "INSERT INTO books (title, author) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewBorrowedBooks() {
        System.out.print("Enter your Member ID: ");
        int memberId = scanner.nextInt();

        try {
            String query = "SELECT b.id, b.title, b.author FROM books b " +
                    "JOIN borrowed_books bb ON b.id = bb.book_id " +
                    "WHERE bb.member_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nBorrowed Books:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("title") + " by " + rs.getString("author"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void viewAllBooks() {
        System.out.println("\nAll Books:");
        try {
            String query = "SELECT * FROM books";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("title") + " by " + rs.getString("author"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllMembers() {
        System.out.println("\nAll Members:");
        try {
            String query = "SELECT * FROM members";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
