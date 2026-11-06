public class VIPTicket extends Ticket {
    private static final double UNIT_PRICE = 150000;
    private static final int DISCOUNT_QUANTITY = 3;
    private static final double DISCOUNT_RATE = 0.15;

    // Input: ticketId, movieTitle, quantity
    // Output: new VIP ticket
    // Approach: constructor chaining with super(...) at the fixed unit price
    public VIPTicket(String ticketId, String movieTitle, int quantity) {
        super("VIP", ticketId, movieTitle, quantity, UNIT_PRICE);
    }

    // Input: none
    // Output: final total in VND
    // Approach: subtotal, minus 15% if quantity >= 3
    @Override
    public double calculateTotalPrice() {
        double rate = getQuantity() >= DISCOUNT_QUANTITY ? DISCOUNT_RATE : 0;
        return calculateSubtotal() * (1 - rate);
    }
}
