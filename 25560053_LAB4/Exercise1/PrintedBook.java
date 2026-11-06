public class PrintedBook extends LibraryItem {
    private static final long FEE_PER_DAY = 5000;
    private static final long FEE_PER_PAGE = 10;
    private int pages;

    // Input: itemId, title, author, daysBorrowed, pages
    // Output: new printed book
    // Approach: super(...), pages via setter
    public PrintedBook(String itemId, String title, String author, int daysBorrowed, int pages) {
        super("Printed book", itemId, title, author, daysBorrowed);
        setPages(pages);
    }

    // Input: pages
    // Output: none, pages updated
    // Approach: < 1 -> exception, else update
    public void setPages(int pages) {
        if (pages < 1) {
            throw new IllegalArgumentException("Pages must be at least 1");
        }
        this.pages = pages;
    }

    // Input: none
    // Output: total borrowing fee in VND
    // Approach: 5,000 x days + 10 x pages
    @Override
    public long calculateFee() {
        return FEE_PER_DAY * getDaysBorrowed() + FEE_PER_PAGE * pages;
    }

    // Input: none
    // Output: prints common info and pages
    // Approach: super.displayInfo(), then pages
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Pages: " + pages);
    }
}
