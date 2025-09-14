# Library Management System

## Introduction

This is a comprehensive Library Management System (LMS) designed to handle the essential functions of a library. It provides a user-friendly interface for managing books, members, and borrowing transactions. The system is built with role-based access control, catering to Administrators, Librarians, and Members, each with specific functionalities.

## Features

* **User Authentication:** Secure user registration and login system.
* **Role-Based Access:**
    * **Administrator:** Can view all books and manage all members.
    * **Librarian:** Can manage the book inventory, including adding new books.
    * **Member:** Can view all books and check their borrowed books.
* **Book Management:** Add, search, and view book details.
* **Member Management:** Manage member information and membership status.
* **Borrowing System:** Handle book borrowing and returns, with fine calculation for overdue books.
* **Reporting:** Generate reports, such as a list of popular books based on borrowing frequency.
* **Web Interface:** A clean and simple web interface for easy interaction with the system.

## Technologies Used

* **Backend:**
    * Java
    * Java Servlets
    * JDBC (Java Database Connectivity)
* **Frontend:**
    * HTML
    * CSS
    * JavaScript
* **Database:**
    * MySQL
* **Dependencies:**
    * MySQL Connector/J
    * Javax Servlet API
    * Gson

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Java Development Kit (JDK) 8 or higher
* Apache Tomcat Server 8 or higher
* MySQL Server
* An IDE like IntelliJ IDEA or Eclipse

### Installation and Configuration

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/LibraryManagementSystem.git](https://github.com/your-username/LibraryManagementSystem.git)
    cd LibraryManagementSystem
    ```

2.  **Database Setup:**
    * Create a MySQL database named `library_db`.
    * Execute the following SQL queries to create the necessary tables:

    ```sql
    CREATE TABLE users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        role VARCHAR(50) NOT NULL
    );

    CREATE TABLE books (
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        genre VARCHAR(100),
        publisher VARCHAR(255),
        stock INT
    );

    CREATE TABLE members (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL,
        membershipType VARCHAR(100),
        isActive BOOLEAN
    );

    CREATE TABLE borrowed_books (
        transactionId INT AUTO_INCREMENT PRIMARY KEY,
        member_id INT,
        book_id INT,
        borrowDate DATE,
        returnDate DATE,
        fine DOUBLE,
        FOREIGN KEY (member_id) REFERENCES members(id),
        FOREIGN KEY (book_id) REFERENCES books(id)
    );
    ```

3.  **Configure Database Connection:**
    * Open the `src/com/library/LoginServlet.java` and `src/com/library/RegisterServlet.java` files.
    * Update the `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` variables with your MySQL database credentials.
    * Do the same for `src/LibrarySystem.java` if you intend to use the console application.

4.  **Add Dependencies:**
    * Make sure the `.jar` files for MySQL Connector/J, Servlet API, and Gson are included in your project's build path. You can find them in the `lib` folder or download them. The project is configured to use:
        * `mysql-connector-j-8.0.32.jar`
        * `javax.servlet-api-3.0.1.jar`
        * `gson-2.11.0.jar`

5.  **Deploy the Application:**
    * Build the project to generate a `.war` file.
    * Deploy the `.war` file to your Apache Tomcat server.

## Usage

1.  Start your Apache Tomcat server.
2.  Open your web browser and navigate to `http://localhost:8080/LibraryManagementSystem/` (the final part of the URL may vary based on your deployment).
3.  Register a new user (e.g., as an Admin, Librarian, or Member).
4.  Log in with the credentials of the user you just created.
5.  You will be redirected to the respective menu based on the user's role.
6.  Explore the features available to your user role.

## Project Structure

