public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();  // Create library instance
        library.addUser(new User(1, "John"));
        library.addUser(new User(2, "Emily"));
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho", 5));
        library.addBook(new Book(2, "Harry Potter", "J.K. Rowling", 3));

        new LibraryManagementSystemUI(library);  // Start UI
    }
}

