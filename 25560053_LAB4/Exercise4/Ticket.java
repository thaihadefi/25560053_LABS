public abstract class Ticket {
    private String type;
    private String ticketId;
    private String movieTitle;
    private int quantity;
    private double unitPrice;

    // Input: type, ticketId, movieTitle, quantity, unitPrice
    // Output: new ticket purchase
    // Approach: empty ID -> exception, numbers via setters
    public Ticket(String type, String ticketId, String movieTitle, int quantity, double unitPrice) {
        if (ticketId.isBlank()) {
            throw new IllegalArgumentException("Ticket ID must not be empty");
        }
        this.type = type;
        this.ticketId = ticketId;
        this.movieTitle = movieTitle;
        setQuantity(quantity);
        setUnitPrice(unitPrice);
    }

    // Input: none
    // Output: type name
    // Approach: return type
    public String getType() {
        return type;
    }

    // Input: none
    // Output: ticket ID
    // Approach: return ticketId
    public String getTicketId() {
        return ticketId;
    }

    // Input: none
    // Output: movie title
    // Approach: return movieTitle
    public String getMovieTitle() {
        return movieTitle;
    }

    // Input: none
    // Output: quantity
    // Approach: return quantity
    public int getQuantity() {
        return quantity;
    }

    // Input: quantity
    // Output: none, quantity updated
    // Approach: < 1 -> exception, else update
    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        this.quantity = quantity;
    }

    // Input: none
    // Output: unit price in VND
    // Approach: return unitPrice
    public double getUnitPrice() {
        return unitPrice;
    }

    // Input: unitPrice
    // Output: none, unit price updated
    // Approach: < 0 -> exception, else update
    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must not be negative");
        }
        this.unitPrice = unitPrice;
    }

    // Input: none
    // Output: quantity x unit price, before discount
    // Approach: multiply the two fields
    public double calculateSubtotal() {
        return quantity * unitPrice;
    }

    // Input: none
    // Output: discount amount in VND
    // Approach: subtotal - final total
    public double calculateDiscount() {
        return calculateSubtotal() - calculateTotalPrice();
    }

    // Input: none
    // Output: final total in VND
    // Approach: implemented by subclasses with their discount rule
    public abstract double calculateTotalPrice();

    // Input: none
    // Output: prints common info
    // Approach: print type, then each field on one line; subclasses add their own
    public void displayInfo() {
        System.out.println("Type: " + type);
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Movie: " + movieTitle);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Unit price: %,.2f VND%n", unitPrice);
    }
}
