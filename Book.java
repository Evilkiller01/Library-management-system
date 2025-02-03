import java.util.Objects;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private int quantity;

    // Constructor
    public Book(int bookID, String title, String author, int quantity) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    // Getters
    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Show book details
    public void displayBook() {
        System.out.println("Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Available: " + quantity);
    }

    // Override equals and hashCode to compare books based on their ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book book = (Book) obj;
        return bookID == book.bookID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }
}
