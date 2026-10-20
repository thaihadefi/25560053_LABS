public class PartialTicket extends Ticket {
    private static final long ENTRANCE_FEE = 70000;
    private static final long PRICE_PER_GAME = 20000;

    // Input: ticketId, holderName, birthYear, gamesPlayed
    // Output: new partial ticket
    // Approach: super(...)
    public PartialTicket(String ticketId, String holderName, int birthYear, int gamesPlayed) {
        super(ticketId, holderName, birthYear, gamesPlayed);
    }

    // Input: none
    // Output: ticket price in VND
    // Approach: 70,000 + 20,000 x games
    @Override
    public long getPrice() {
        return ENTRANCE_FEE + PRICE_PER_GAME * getGamesPlayed();
    }
}
