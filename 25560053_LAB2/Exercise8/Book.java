public class Book {
    // Attributes
    private String title;
    private double price;
    private Author author;

    // Parameterized constructor
    // Input: title, price, author
    // Output: new book
    // Purpose: book linked to its author
    // Approach: price < 0 -> 0
    public Book(String title, double price, Author author) {
        this.title = title;
        this.price = price >= 0 ? price : 0;
        this.author = author;
    }

    // Methods
    // Input: none
    // Output: prints title, author, price
    // Purpose: show book
    // Approach: print fields, author.getName()
    public void displayInfo() {
        System.out.println(title + " - by " + author.getName() + " - " + price);
    }
}
