public class RegularTicket extends Ticket {
    private static final double UNIT_PRICE = 80000;
    private static final int DISCOUNT_QUANTITY = 5;
    private static final double DISCOUNT_RATE = 0.10;

    // Input: ticketId, movieTitle, quantity
    // Output: new regular ticket
    // Approach: constructor chaining with super(...) at the fixed unit price
    public RegularTicket(String ticketId, String movieTitle, int quantity) {
        super("Regular", ticketId, movieTitle, quantity, UNIT_PRICE);
    }

    // Input: none
    // Output: final total in VND
    // Approach: subtotal, minus 10% if quantity >= 5
    @Override
    public double calculateTotalPrice() {
        double rate = getQuantity() >= DISCOUNT_QUANTITY ? DISCOUNT_RATE : 0;
        return calculateSubtotal() * (1 - rate);
    }
}
