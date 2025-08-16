import java.util.List;
import java.util.stream.Collectors;

public class BookSearch {
    public static List<Book> searchByTitle(List<Book> books, String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public static List<Book> searchByAuthor(List<Book> books, String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
}
