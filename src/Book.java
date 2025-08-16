public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private int stock;

    public Book(int id, String title, String author, String genre, String publisher, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.stock = stock;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getPublisher() { return publisher; }
    public int getStock() { return stock; }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }
}

