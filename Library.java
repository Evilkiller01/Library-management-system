import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private List<String> history;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully: " + book.getTitle());
    }

    // Search for a book by title or author
    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) || book.getAuthor().equalsIgnoreCase(keyword)) {
                return book;
            }
        }
        return null; // Book not found
    }

    // Borrow a book
    public boolean borrowBook(int userID, int bookID) {
        User user = findUserByID(userID);
        Book book = findBookByID(bookID);
        if (book != null && user != null) {
            if (book.getQuantity() > 0) {
                user.borrowBook(book);
                book.setQuantity(book.getQuantity() - 1);
                history.add(user.getName() + " borrowed " + book.getTitle());
                System.out.println("Book borrowed successfully: " + book.getTitle());
                return true; // Success
            } else {
                System.out.println("Sorry, book is currently unavailable.");
            }
        } else {
            System.out.println("User or Book not found.");
        }
        return false; // Failure
    }

    // Return a book
    public boolean returnBook(int userID, int bookID) {
        User user = findUserByID(userID);
        Book book = findBookByID(bookID);
        if (book != null && user != null) {
            if (user.getBorrowedBooks().contains(book)) {
                user.returnBook(book);
                book.setQuantity(book.getQuantity() + 1);
                history.add(user.getName() + " returned " + book.getTitle());
                System.out.println("Book returned successfully: " + book.getTitle());
                return true; // Success
            } else {
                System.out.println("User has not borrowed this book.");
            }
        } else {
            System.out.println("User or Book not found.");
        }
        return false; // Failure
    }

    // View borrowing history
    public void viewHistory() {
        System.out.println("Borrowing History:");
        for (String record : history) {
            System.out.println(record);
        }
    }
    // New method to get borrowing history (this is the magic part 🪄)
    public List<String> getHistory() {
        return history; // Return the list of history records
    }

    // Register a new user
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully: " + user.getName());
    }

    // Find a user by ID
    private User findUserByID(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null; // User not found
    }

    // Find a book by ID
    private Book findBookByID(int bookID) {
        for (Book book : books) {
            if (book.getBookID() == bookID) {
                return book;
            }
        }
        return null; // Book not found
    }

    // Display all books
    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            book.displayBook();
        }
    }
    // Get the list of books (newly added method)
    public List<Book> getBooks() {
        return books; // Return the list of books
    }
}

