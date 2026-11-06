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

    // Input: tickets, id
    // Output: ticket with this ID, null if not found
    // Approach: loop, compare IDs ignoring case
    private static Ticket findTicket(ArrayList<Ticket> tickets, String id) {
        for (Ticket t : tickets) {
            if (t.getTicketId().equalsIgnoreCase(id)) {
                return t;
            }
        }
        return null;
    }

    // Input: sc, tickets
    // Output: none, N new purchases added
    // Approach: read each purchase, repeat the ID until it is not a duplicate
    private static void addTickets(Scanner sc, ArrayList<Ticket> tickets) {
        int n = readInt(sc, "Enter number of purchases: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nPurchase " + (i + 1));
            int type = readInt(sc, "Type (1-Regular, 2-VIP): ", 1, 2);
            String id = readText(sc, "Ticket ID: ");
            while (findTicket(tickets, id) != null) {
                System.out.println("Ticket ID already exists!");
                id = readText(sc, "Ticket ID: ");
            }
            String title = readText(sc, "Movie title: ");
            int quantity = readInt(sc, "Quantity: ", 1, Integer.MAX_VALUE);

            if (type == 1) {
                tickets.add(new RegularTicket(id, title, quantity));
            } else {
                tickets.add(new VIPTicket(id, title, quantity));
            }
        }
    }

    // Input: ticket
    // Output: prints the purchase, discount and final total
    // Approach: displayInfo() and calculateTotalPrice() on a Ticket reference
    private static void display(Ticket ticket) {
        ticket.displayInfo();
        System.out.printf("Discount: %,.2f VND%n", ticket.calculateDiscount());
        System.out.printf("Final total: %,.2f VND%n", ticket.calculateTotalPrice());
    }

    // Input: tickets
    // Output: prints every purchase
    // Approach: loop and call display()
    private static void displayAll(ArrayList<Ticket> tickets) {
        for (Ticket t : tickets) {
            System.out.println();
            display(t);
        }
    }

    // Input: tickets, title
    // Output: prints purchases of the movie or a not-found message
    // Approach: loop, compare titles ignoring case
    private static void displayMovie(ArrayList<Ticket> tickets, String title) {
        boolean found = false;
        for (Ticket t : tickets) {
            if (t.getMovieTitle().equalsIgnoreCase(title)) {
                System.out.println();
                display(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No purchase for this movie.");
        }
    }

    // Input: tickets
    // Output: total revenue in VND
    // Approach: loop, add calculateTotalPrice()
    private static double totalRevenue(ArrayList<Ticket> tickets) {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.calculateTotalPrice();
        }
        return total;
    }

    // Input: tickets, title
    // Output: revenue of one movie in VND
    // Approach: overload of totalRevenue(), add only purchases of this title
    private static double totalRevenue(ArrayList<Ticket> tickets, String title) {
        double total = 0;
        for (Ticket t : tickets) {
            if (t.getMovieTitle().equalsIgnoreCase(title)) {
                total += t.calculateTotalPrice();
            }
        }
        return total;
    }

    // Input: tickets (not empty)
    // Output: movie title with the highest aggregated revenue
    // Approach: for each purchase, compute its movie's revenue and keep the max
    private static String findTopMovie(ArrayList<Ticket> tickets) {
        String best = tickets.get(0).getMovieTitle();
        for (Ticket t : tickets) {
            if (totalRevenue(tickets, t.getMovieTitle()) > totalRevenue(tickets, best)) {
                best = t.getMovieTitle();
            }
        }
        return best;
    }

    // Input: tickets
    // Output: new list sorted from highest to lowest final total
    // Approach: insertion sort on a copy, swap left while the previous total is smaller,
    // equal totals keep their input order and the stored order is unchanged
    private static ArrayList<Ticket> sortByTotal(ArrayList<Ticket> tickets) {
        ArrayList<Ticket> sorted = new ArrayList<>(tickets);
        for (int i = 1; i < sorted.size(); i++) {
            int j = i;
            while (j > 0 && sorted.get(j - 1).calculateTotalPrice() < sorted.get(j).calculateTotalPrice()) {
                Ticket temp = sorted.get(j - 1);
                sorted.set(j - 1, sorted.get(j));
                sorted.set(j, temp);
                j--;
            }
        }
        return sorted;
    }

    // Input: menu choices and purchase info from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Ticket> tickets = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add purchases  2. Display all  3. Search by movie  "
                    + "4. Statistics  5. Display by total (high to low)  0. Exit");
            int choice = readInt(sc, "Choice: ", 0, 5);

            if (choice == 0) {
                break;
            }
            if (choice != 1 && tickets.isEmpty()) {
                System.out.println("The list is empty.");
                continue;
            }

            switch (choice) {
                case 1:
                    addTickets(sc, tickets);
                    break;
                case 2:
                    displayAll(tickets);
                    break;
                case 3:
                    displayMovie(tickets, readText(sc, "Movie title: "));
                    break;
                case 4:
                    String top = findTopMovie(tickets);
                    System.out.printf("Total revenue: %,.2f VND%n", totalRevenue(tickets));
                    System.out.printf("Top movie: %s (%,.2f VND)%n", top, totalRevenue(tickets, top));
                    break;
                case 5:
                    displayAll(sortByTotal(tickets));
                    break;
            }
        }

        sc.close();
    }
}
