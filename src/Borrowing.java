import java.util.Date;

public class Borrowing {
    private int transactionId;
    private int memberId;
    private int bookId;
    private Date borrowDate;
    private Date returnDate;
    private double fine;

    public Borrowing(int transactionId, int memberId, int bookId, Date borrowDate, Date returnDate) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.fine = 0.0;
    }

    // Getters and setters
    public int getTransactionId() { return transactionId; }
    public int getMemberId() { return memberId; }
    public int getBookId() { return bookId; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getReturnDate() { return returnDate; }

    public void calculateFine(Date currentDate) {
        if (currentDate.after(returnDate)) {
            long overdueDays = (currentDate.getTime() - returnDate.getTime()) / (1000 * 60 * 60 * 24);
            fine = overdueDays * 2.0; // Assume $2 per day
        }
    }

    public double getFine() { return fine; }
}
