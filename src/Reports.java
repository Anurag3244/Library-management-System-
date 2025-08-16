import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Reports {
    public static Map<String, Long> generatePopularBooks(List<Borrowing> borrowings, List<Book> books) {
        return borrowings.stream()
                .collect(Collectors.groupingBy(
                        borrowing -> books.stream()
                                .filter(book -> book.getId() == borrowing.getBookId())
                                .findFirst()
                                .orElse(null)
                                .getTitle(),
                        Collectors.counting()));
    }
}
