import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Input: sc, prompt
    // Output: non-empty line read from keyboard
    // Approach: repeat until the line is not blank
    private static String readText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("This field cannot be empty!");
        }
    }

    // Input: sc, prompt, min, max
    // Output: integer in [min, max] read from keyboard
    // Approach: repeat until the line is an integer in range
    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    // Input: list of tickets
    // Output: total revenue in VND
    // Approach: loop, add getPrice()
    private static long totalRevenue(ArrayList<Ticket> tickets) {
        long total = 0;
        for (Ticket t : tickets) {
            total += t.getPrice();
        }
        return total;
    }

    // Input: list of tickets
    // Output: number of partial tickets
    // Approach: loop, count instanceof PartialTicket
    private static int countPartial(ArrayList<Ticket> tickets) {
        int count = 0;
        for (Ticket t : tickets) {
            if (t instanceof PartialTicket) {
                count++;
            }
        }
        return count;
    }

    // Input: n, then type and info of each ticket from keyboard
    // Output: total revenue, number of partial tickets
    // Approach: read into ArrayList<Ticket>, then call helpers
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Ticket> tickets = new ArrayList<>();

        int n = readInt(sc, "Enter number of tickets: ", 1, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            System.out.println("\nTicket " + (i + 1));
            int type = readInt(sc, "Type (1-Full Package, 2-Partial): ", 1, 2);
            String id = readText(sc, "Ticket ID: ");
            String name = readText(sc, "Holder name: ");
            int birthYear = readInt(sc, "Year of birth: ", Ticket.MIN_BIRTH_YEAR, Year.now().getValue());
            int games = readInt(sc, "Games played: ", 0, Ticket.TOTAL_GAMES);

            if (type == 1) {
                tickets.add(new FullPackageTicket(id, name, birthYear, games));
            } else {
                tickets.add(new PartialTicket(id, name, birthYear, games));
            }
        }

        System.out.println("\nTotal revenue: " + totalRevenue(tickets) + " VND");
        System.out.println("Partial tickets sold: " + countPartial(tickets));

        sc.close();
    }
}
