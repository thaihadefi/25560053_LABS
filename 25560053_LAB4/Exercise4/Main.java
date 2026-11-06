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

    // Input: tickets, movieTitle
    // Output: purchases of this movie
    // Approach: loop, keep matches ignoring case
    private static ArrayList<Ticket> findByMovie(ArrayList<Ticket> tickets, String movieTitle) {
        ArrayList<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getMovieTitle().equalsIgnoreCase(movieTitle)) {
                result.add(t);
            }
        }
        return result;
    }

    // Input: tickets, ascending
    // Output: new list sorted by final total
    // Approach: insertion sort: swap with the previous one while out of order
    private static ArrayList<Ticket> sortByValue(ArrayList<Ticket> tickets, boolean ascending) {
        ArrayList<Ticket> sorted = new ArrayList<>(tickets);
        for (int i = 1; i < sorted.size(); i++) {
            int j = i;
            while (j > 0 && (ascending ? sorted.get(j - 1).calculateTotalPrice() > sorted.get(j).calculateTotalPrice()
                    : sorted.get(j - 1).calculateTotalPrice() < sorted.get(j).calculateTotalPrice())) {
                Ticket temp = sorted.get(j - 1);
                sorted.set(j - 1, sorted.get(j));
                sorted.set(j, temp);
                j--;
            }
        }
        return sorted;
    }

    // Input: tickets (not empty)
    // Output: movie title with the highest total revenue
    // Approach: for each purchase, total all purchases of its movie and keep the best
    private static String findTopMovie(ArrayList<Ticket> tickets) {
        String bestMovie = null;
        double bestTotal = -1;
        for (Ticket t : tickets) {
            double sum = total(tickets, t.getMovieTitle());
            if (sum > bestTotal) {
                bestTotal = sum;
                bestMovie = t.getMovieTitle();
            }
        }
        return bestMovie;
    }

    // Input: tickets
    // Output: total revenue in VND
    // Approach: sum calculateTotalPrice() on each Ticket reference
    private static double total(ArrayList<Ticket> tickets) {
        double sum = 0;
        for (Ticket t : tickets) {
            sum += t.calculateTotalPrice();
        }
        return sum;
    }

    // Input: tickets, movieTitle
    // Output: total revenue of one movie in VND
    // Approach: overload of total(), total of findByMovie()
    private static double total(ArrayList<Ticket> tickets, String movieTitle) {
        return total(findByMovie(tickets, movieTitle));
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
                System.out.println("ID already exists!");
                id = readText(sc, "Ticket ID: ");
            }
            String movieTitle = readText(sc, "Movie title: ");
            int quantity = readInt(sc, "Quantity: ", 1, Integer.MAX_VALUE);

            if (type == 1) {
                tickets.add(new RegularTicket(id, movieTitle, quantity));
            } else {
                tickets.add(new VIPTicket(id, movieTitle, quantity));
            }
        }
    }

    // Input: ticket
    // Output: prints the purchase, discount and final total, or Not found.
    // Approach: displayInfo(), calculateDiscount(), calculateTotalPrice() on a Ticket reference
    private static void display(Ticket ticket) {
        if (ticket == null) {
            System.out.println("Not found.");
            return;
        }
        ticket.displayInfo();
        System.out.printf("Discount: %,.2f VND%n", ticket.calculateDiscount());
        System.out.printf("Final total: %,.2f VND%n", ticket.calculateTotalPrice());
    }

    // Input: tickets
    // Output: prints every purchase, or Not found. if empty
    // Approach: overload of display(), loop and call display(ticket)
    private static void display(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("Not found.");
        }
        for (Ticket t : tickets) {
            System.out.println();
            display(t);
        }
    }

    // Input: tickets (not empty)
    // Output: prints total revenue and the top movie
    // Approach: total(), then findTopMovie() and total(tickets, movie)
    private static void printStatistics(ArrayList<Ticket> tickets) {
        String topMovie = findTopMovie(tickets);
        System.out.printf("Total revenue: %,.2f VND%n", total(tickets));
        System.out.printf("Top movie: %s (%,.2f VND)%n", topMovie, total(tickets, topMovie));
    }

    // Input: none
    // Output: prints the menu
    // Approach: one line per option, 0 exits
    private static void printMenu() {
        System.out.println("\n=== Cinema Ticket Sales ===");
        System.out.println("1. Add purchases");
        System.out.println("2. Display all");
        System.out.println("3. Find by movie title");
        System.out.println("4. Statistics");
        System.out.println("5. Sort by final total (highest first)");
        System.out.println("0. Exit");
    }

    // Input: menu choices and data from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Ticket> tickets = new ArrayList<>();

        while (true) {
            printMenu();
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
                    display(tickets);
                    break;
                case 3:
                    display(findByMovie(tickets, readText(sc, "Movie title: ")));
                    break;
                case 4:
                    printStatistics(tickets);
                    break;
                case 5:
                    display(sortByValue(tickets, false));
                    break;
            }
        }

        sc.close();
    }
}
