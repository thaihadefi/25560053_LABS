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
    // Output: type
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
    // Output: unit price
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
    // Output: price before discount in VND
    // Approach: quantity x unit price
    public double calculateSubtotal() {
        return quantity * unitPrice;
    }

    // Input: none
    // Output: discount amount in VND
    // Approach: subtotal - calculateTotalPrice(), the subclass rule decides the discount
    public double calculateDiscount() {
        return calculateSubtotal() - calculateTotalPrice();
    }

    // Input: none
    // Output: final total in VND
    // Approach: implemented by subclasses
    public abstract double calculateTotalPrice();

    // Input: none
    // Output: prints ID, type, movie, quantity, unit price
    // Approach: one field per line
    public void displayInfo() {
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Type: " + type);
        System.out.println("Movie Title: " + movieTitle);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Unit Price: %,.2f VND%n", unitPrice);
    }
}
