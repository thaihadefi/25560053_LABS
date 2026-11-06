public class PrintedBook extends LibraryItem {
    private static final long FEE_PER_DAY = 5000;
    private static final long FEE_PER_PAGE = 10;
    private int numberOfPages;

    // Input: itemId, title, author, daysBorrowed, numberOfPages
    // Output: new printed book
    // Approach: super(...), pages via setter
    public PrintedBook(String itemId, String title, String author, int daysBorrowed, int numberOfPages) {
        super("Printed Book", itemId, title, author, daysBorrowed);
        setNumberOfPages(numberOfPages);
    }

    // Input: none
    // Output: number of pages
    // Approach: return numberOfPages
    public int getNumberOfPages() {
        return numberOfPages;
    }

    // Input: numberOfPages
    // Output: none, number of pages updated
    // Approach: < 1 -> exception, else update
    public void setNumberOfPages(int numberOfPages) {
        if (numberOfPages < 1) {
            throw new IllegalArgumentException("Number of pages must be at least 1");
        }
        this.numberOfPages = numberOfPages;
    }

    @Override
    // Input: none
    // Output: total borrowing fee in VND
    // Approach: 5,000 x days + 10 x pages
    public long calculateFee() {
        return FEE_PER_DAY * getDaysBorrowed() + FEE_PER_PAGE * numberOfPages;
    }

    @Override
    // Input: none
    // Output: prints common info and pages
    // Approach: super.displayInfo(), then pages
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Pages: " + numberOfPages);
    }
}