public abstract class LibraryItem {
    private String type;
    private String itemId;
    private String title;
    private String author;
    private int daysBorrowed;

    // Input: type, itemId, title, author, daysBorrowed
    // Output: new library item
    // Approach: empty ID -> exception, days via setter
    public LibraryItem(String type, String itemId, String title, String author, int daysBorrowed) {
        if (itemId.isBlank()) {
            throw new IllegalArgumentException("Item ID must not be empty");
        }
        this.type = type;
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        setDaysBorrowed(daysBorrowed);
    }

    // Input: none
    // Output: type name
    // Approach: return type
    public String getType() {
        return type;
    }

    // Input: none
    // Output: item ID
    // Approach: return itemId
    public String getItemId() {
        return itemId;
    }

    // Input: none
    // Output: number of days borrowed
    // Approach: return daysBorrowed
    public int getDaysBorrowed() {
        return daysBorrowed;
    }

    // Input: daysBorrowed
    // Output: none, days updated
    // Approach: < 1 -> exception, else update
    public void setDaysBorrowed(int daysBorrowed) {
        if (daysBorrowed < 1) {
            throw new IllegalArgumentException("Days borrowed must be at least 1");
        }
        this.daysBorrowed = daysBorrowed;
    }

    // Input: none
    // Output: total borrowing fee in VND
    // Approach: implemented by subclasses
    public abstract long calculateFee();

    // Input: none
    // Output: prints common info
    // Approach: print type, then each field on one line; subclasses add their own
    public void displayInfo() {
        System.out.println("Type: " + type);
        System.out.println("ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Days borrowed: " + daysBorrowed);
    }
}
