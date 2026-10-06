public class Main {
    // Input: none
    // Output: each author with their books
    // Purpose: test Author and Book
    // Approach: create books, addBook() to authors, display
    public static void main(String[] args) {
        Author a1 = new Author("Nguyen Nhat Anh", "nna@example.com");
        Author a2 = new Author("To Hoai", "tohoai@example.com");

        Book b1 = new Book("Mat Biec", 110000, a1);
        Book b2 = new Book("Cho Toi Xin Mot Ve Di Tuoi Tho", 85000, a1);
        Book b3 = new Book("De Men Phieu Luu Ky", 60000, a2);

        a1.addBook(b1);
        a1.addBook(b2);
        a2.addBook(b3);

        a1.displayInfo();
        a2.displayInfo();
    }
}
