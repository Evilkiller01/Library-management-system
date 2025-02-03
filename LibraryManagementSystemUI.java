import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryManagementSystemUI extends JFrame{
    private Library library;
    private JFrame frame;
    private JPanel panel;
    private JPanel contentPane;
    private JTextField userIdField, bookIdField, searchField;
    private JTextArea outputArea;
    private JButton buttonOK; // or the appropriate type you're using
    private JButton buttonCancel; // Declare the buttonCancel field


    public LibraryManagementSystemUI(Library library) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel(); // Initialize the contentPane
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout()); // Set layout for the content pane

        // Initialize buttonOK
        buttonOK = new JButton("OK"); // Create the button
        contentPane.add(buttonOK, BorderLayout.SOUTH); // Add the button to the bottom of the content pane

        buttonCancel = new JButton("Cancel"); // Create the button
        contentPane.add(buttonCancel, BorderLayout.NORTH); // Add the button to your contentPane

        buttonCancel.addActionListener(e -> {
            // Define what happens when the cancel button is clicked
            System.exit(0); // Close the application
        });

        // Add action listener for button
        buttonOK.addActionListener(e -> {
            // Define what happens when the button is clicked
            JOptionPane.showMessageDialog(this, "Button OK clicked!");


        });
        this.library = library;
        initializeUI();
    }

    private void initializeUI() {
        // Set up the main frame
        frame = new JFrame("Library Management System");
        panel = new JPanel();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components for user input
        userIdField = new JTextField(10);
        bookIdField = new JTextField(10);
        searchField = new JTextField(15);

        // Output area
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        buttonOK = new JButton("OK"); // Initialize the button
        contentPane.add(buttonOK); // Add the button to your contentPane


        // Buttons for actions
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton searchButton = new JButton("Search Book");
        JButton displayBooksButton = new JButton("Display All Books");
        JButton historyButton = new JButton("View History");

        // Add listeners to buttons
        borrowButton.addActionListener(new BorrowBookAction());
        returnButton.addActionListener(new ReturnBookAction());
        searchButton.addActionListener(new SearchBookAction());
        displayBooksButton.addActionListener(new DisplayBooksAction());
        historyButton.addActionListener(new ViewHistoryAction());

        // Layout the components
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("Book ID:"));
        panel.add(bookIdField);
        panel.add(borrowButton);
        panel.add(returnButton);
        panel.add(new JLabel("Search Book by Title/Author:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(displayBooksButton);
        panel.add(historyButton);

        // Add the output area
        panel.add(new JScrollPane(outputArea));

        // Add the panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }

    // Action for borrowing a book
    class BorrowBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());
                library.borrowBook(userId, bookId);
                outputArea.setText("Book borrowed successfully.\n");
            } catch (NumberFormatException ex) {
                outputArea.setText("Please enter valid User ID and Book ID.\n");
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage() + "\n");
            }
        }
    }

    // Action for returning a book
    class ReturnBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());
                library.returnBook(userId, bookId);
                outputArea.setText("Book returned successfully.\n");
            } catch (NumberFormatException ex) {
                outputArea.setText("Please enter valid User ID and Book ID.\n");
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage() + "\n");
            }
        }
    }

    // Action for searching a book
    class SearchBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String keyword = searchField.getText();
            Book foundBook = library.searchBook(keyword);
            if (foundBook != null) {
                outputArea.setText("Book found:\n" + foundBook.getTitle() + " by " + foundBook.getAuthor() + " (Available: " + foundBook.getQuantity() + ")\n");
            } else {
                outputArea.setText("Book not found.\n");
            }
        }
    }

    // Action for displaying all books
    class DisplayBooksAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<Book> books = library.getBooks();
            StringBuilder output = new StringBuilder("Library Books:\n");
            for (Book book : books) {
                output.append("ID: ").append(book.getBookID())
                        .append(", Title: ").append(book.getTitle())
                        .append(", Author: ").append(book.getAuthor())
                        .append(", Available: ").append(book.getQuantity()).append("\n");
            }
            outputArea.setText(output.toString());
        }
    }

    // Action for viewing borrowing history
    class ViewHistoryAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<String> history = library.getHistory();
            StringBuilder output = new StringBuilder("Borrowing History:\n");
            for (String record : history) {
                output.append(record).append("\n");
            }
            outputArea.setText(output.toString());
        }
    }

    // Main method for launching the UI
    public static void main(String[] args) {
        Library library = new Library();

        // Adding sample users
        library.addUser(new User(1, "Siddhant"));
        library.addUser(new User(2, "Dhruv"));
        library.addUser(new User(3, "Harsh"));

        // Adding sample books
        library.addBook(new Book(1, "The God of Small Things", "Arundhati Roy", 3));
        library.addBook(new Book(2, "A Suitable Boy", "Vikram Seth", 2));
        library.addBook(new Book(3, "Gitanjali", "Rabindranath Tagore", 5));
        library.addBook(new Book(4, "Interpreter of Maladies", "Jhumpa Lahiri", 1));
        library.addBook(new Book(5, "Five Point Someone", "Chetan Bhagat", 6));

        // Launch the UI
        new LibraryManagementSystemUI(library);
    }
}
