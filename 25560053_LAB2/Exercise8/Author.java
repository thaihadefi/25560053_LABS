import java.util.ArrayList;
import java.util.List;

public class Author {
    // Attributes
    private String name;
    private String email;
    private List<Book> books;

    // Parameterized constructor
    // Input: name, email
    // Output: new author
    // Purpose: new author, no books yet
    // Approach: empty ArrayList
    public Author(String name, String email) {
        this.name = name;
        this.email = email;
        this.books = new ArrayList<>();
    }

    // Methods
    // Input: none
    // Output: name
    // Purpose: used by Book
    // Approach: return name
    public String getName() {
        return name;
    }

    // Input: book
    // Output: none, book is added
    // Purpose: add a book
    // Approach: books.add(book)
    public void addBook(Book book) {
        books.add(book);
    }

    // Input: none
    // Output: prints author and books
    // Purpose: show author
    // Approach: print fields, then each book
    public void displayInfo() {
        System.out.println(name + " (" + email + ") - " + books.size() + " book(s)");
        for (Book b : books) {
            System.out.print("  ");
            b.displayInfo();
        }
    }
}
