import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookManager {

    public static void main(String[] args) {
        List<Book> library = new ArrayList<>();

        // Adding books to the library with read status
        library.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", false));
        library.add(new Book("To Kill a Mockingbird", "Harper Lee", true));
        library.add(new Book("1984", "George Orwell", false));
        library.add(new Book("Pride and Prejudice", "Jane Austen", true));

        // Displaying the list of books
        displayBooks(library);

        // Search loop
        String searchType = prompt("Enter search type (title/author/read) or 'exit': ");
        while (!searchType.equalsIgnoreCase("exit")) {
            switch (searchType.toLowerCase()) {
                case "title":
                    searchByTitle(library, prompt("Enter title to search: "));
                    break;
                case "author":
                    searchByAuthor(library, prompt("Enter author to search: "));
                    break;
                case "read":
                    searchByReadStatus(library, prompt("Enter read status (true/false): "));
                    break;
                default:
                    System.out.println("Invalid search type. Please enter 'title', 'author', 'read', or 'exit'.");
            }
            searchType = prompt("Enter search type (title/author/read): ");
        }
    }

    private static void searchByTitle(List<Book> library, String searchTitle) {
        boolean found = false;
        for (Book book : library) {
            if (book.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                System.out.println("Search Results:");
                displayBookDetails(book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No books found matching: " + searchTitle);
        }
    }

    private static void searchByAuthor(List<Book> library, String searchAuthor) {
        boolean found = false;
        for (Book book : library) {
            if (book.getAuthor().toLowerCase().contains(searchAuthor.toLowerCase())) {
                System.out.println("Search Results:");
                displayBookDetails(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching author: " + searchAuthor);
        }
    }

    private static void searchByReadStatus(List<Book> library, String readStatus) {
        boolean status = readStatus.equalsIgnoreCase("true");
        boolean found = false;
        for (Book book : library) {
            if (book.isRead() == status) {
                System.out.println("Search Results:");
                displayBookDetails(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with read status: " + readStatus);
        }
    }

    // Existing methods (displayBooks, displayBookDetails, prompt, readLine, and Book class) remain the same.
    // Display all books in the library
    private static void displayBooks(List<Book> library) {
        System.out.println("My Library:");
        for (Book book : library) {
            displayBookDetails(book);
        }
    }

    // Display details of a specific book
    private static void displayBookDetails(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Status: " + (book.isRead() ? "Read" : "Not Read"));
        System.out.println("-----");
    }

    // Prompts the user for a search term
    private static String prompt(String message) {
        System.out.print(message);
        return readLine();
    }

    // Reads a line from the terminal
    private static String readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Define the Book class to encapsulate book details
    static class Book {
        private String title;
        private String author;
        private boolean isRead;

        public Book(String title, String author, boolean isRead) {
            this.title = title;
            this.author = author;
            this.isRead = isRead;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }
    }

}