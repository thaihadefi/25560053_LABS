import java.time.Year;

public abstract class Ticket {
    public static final int TOTAL_GAMES = 30;
    public static final int MIN_BIRTH_YEAR = 1900;
    private String ticketId;
    private String holderName;
    private int birthYear;
    private int gamesPlayed;

    // Input: ticketId, holderName, birthYear, gamesPlayed
    // Output: new ticket
    // Approach: birthYear and gamesPlayed via setters
    public Ticket(String ticketId, String holderName, int birthYear, int gamesPlayed) {
        this.ticketId = ticketId;
        this.holderName = holderName;
        setBirthYear(birthYear);
        setGamesPlayed(gamesPlayed);
    }

    // Input: birthYear
    // Output: none, year updated if valid
    // Approach: in [MIN_BIRTH_YEAR, current year] -> update, else print error
    public void setBirthYear(int birthYear) {
        if (birthYear >= MIN_BIRTH_YEAR && birthYear <= Year.now().getValue()) {
            this.birthYear = birthYear;
        } else {
            System.out.println("Year of birth must be from " + MIN_BIRTH_YEAR + " to the current year!");
        }
    }

    // Input: none
    // Output: number of games played
    // Approach: return gamesPlayed
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    // Input: gamesPlayed
    // Output: none, games updated if valid
    // Approach: in [0, TOTAL_GAMES] -> update, else print error
    public void setGamesPlayed(int gamesPlayed) {
        if (gamesPlayed >= 0 && gamesPlayed <= TOTAL_GAMES) {
            this.gamesPlayed = gamesPlayed;
        } else {
            System.out.println("Games played must be from 0 to " + TOTAL_GAMES + "!");
        }
    }

    // Input: none
    // Output: ticket price in VND
    // Approach: implemented by subclasses
    public abstract long getPrice();
}
