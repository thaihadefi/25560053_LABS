public class Magazine extends LibraryItem {
    private static final long FEE_PER_DAY = 3000;
    private static final long FIXED_FEE = 2000;
    private int issueNumber;

    // Input: itemId, title, author, daysBorrowed, issueNumber
    // Output: new magazine
    // Approach: super(...), issue number via setter
    public Magazine(String itemId, String title, String author, int daysBorrowed, int issueNumber) {
        super("Magazine", itemId, title, author, daysBorrowed);
        setIssueNumber(issueNumber);
    }

    // Input: issueNumber
    // Output: none, issue number updated
    // Approach: < 1 -> exception, else update
    public void setIssueNumber(int issueNumber) {
        if (issueNumber < 1) {
            throw new IllegalArgumentException("Issue number must be at least 1");
        }
        this.issueNumber = issueNumber;
    }

    // Input: none
    // Output: total borrowing fee in VND
    // Approach: 3,000 x days + 2,000
    @Override
    public long calculateFee() {
        return FEE_PER_DAY * getDaysBorrowed() + FIXED_FEE;
    }

    // Input: none
    // Output: prints common info and issue number
    // Approach: super.displayInfo(), then issue number
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Issue number: " + issueNumber);
    }
}
