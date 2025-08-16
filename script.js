// Fetching books dynamically
  const booksList = document.getElementById('books-list');
  if (booksList) {
    const books = [
      { id: 1, title: 'The Catcher in the Rye', author: 'J.D. Salinger' },
      { id: 2, title: 'To Kill a Mockingbird', author: 'Harper Lee' },
      { id: 3, title: '1984', author: 'George Orwell' },
      { id: 4, title: 'Moby-Dick', author: 'Herman Melville' },
    ];

    books.forEach(book => {
      const bookDiv = document.createElement('div');
      bookDiv.className = 'book-item';
      bookDiv.innerHTML = `
        <h3>${book.title}</h3>
        <p>By: ${book.author}</p>
      `;
      booksList.appendChild(bookDiv);
    });
  }

  // Form submission (Contact page)
  function submitForm(event) {
    event.preventDefault();
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;

    const status = document.getElementById('message-status');
    status.innerHTML = `<p>Thanks, ${name}! We will contact you at ${email} soon.</p>`;
  }

  // Local storage mock database for registered users
  let users = [];

  // Handle Registration
  function handleRegister(event) {
    event.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const role = document.getElementById("role").value;

    if (users.find(user => user.username === username)) {
      alert("Username already exists!");
      return;
    }

    users.push({ username, password, role });
    alert("Registration successful! You can now login.");
    window.location.href = "login.html";
  }

  // Handle Login
  function handleLogin(event) {
    event.preventDefault();

    const username = document.getElementById("login-username").value.trim();
    const password = document.getElementById("login-password").value.trim();

    const user = users.find(user => user.username === username && user.password === password);

    if (!user) {
      alert("Invalid username or password!");
      return;
    }

    alert(`Welcome, ${user.role}! Redirecting to your menu...`);

    if (user.role === "Admin") {
      adminMenu();
    } else if (user.role === "Librarian") {
      librarianMenu();
    } else if (user.role === "Member") {
      memberMenu();
    }
  }

  // Admin Menu Function
  function adminMenu() {
    document.body.innerHTML = `
      <h2>Welcome, Admin</h2>
      <p>This is the Admin Menu.</p>
      <a href="index.html">Logout</a>
    `;
  }

  // Librarian Menu Function
  function librarianMenu() {
    document.body.innerHTML = `
      <h2>Welcome, Librarian</h2>
      <p>This is the Librarian Menu.</p>
      <a href="index.html">Logout</a>
    `;
  }

  // Member Menu Function
  function memberMenu() {
    document.body.innerHTML = `
      <h2>Welcome, Member</h2>
      <p>This is the Member Menu.</p>
      <a href="index.html">Logout</a>
    `;
  }