public class FullPackageTicket extends Ticket {
    private static final long PRICE = 200000;

    // Input: ticketId, holderName, birthYear, gamesPlayed
    // Output: new full package ticket
    // Approach: super(...)
    public FullPackageTicket(String ticketId, String holderName, int birthYear, int gamesPlayed) {
        super(ticketId, holderName, birthYear, gamesPlayed);
    }

    // Input: none
    // Output: 200,000
    // Approach: fixed price, does not depend on games
    @Override
    public long getPrice() {
        return PRICE;
    }
}
