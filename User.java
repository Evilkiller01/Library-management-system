import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int userID;
    private String name;
    private List<Book> borrowedBooks;

    // Constructor
    public User(int userID, String name) {
        this.userID = userID;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Borrow a book
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // Return a book
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    // Show user's borrowed books
    public void displayBorrowedBooks() {
        System.out.println("Books borrowed by " + name + ":");
        for (Book book : borrowedBooks) {
            book.displayBook();
        }
    }

    // Override equals and hashCode to compare users based on their ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        return userID == user.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
}
