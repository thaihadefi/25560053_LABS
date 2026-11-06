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

    // Input: items, id
    // Output: item with this ID, null if not found
    // Approach: loop, compare IDs ignoring case
    private static LibraryItem findItem(ArrayList<LibraryItem> items, String id) {
        for (LibraryItem item : items) {
            if (item.getItemId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    // Input: sc, items
    // Output: none, N new items added
    // Approach: read each item, repeat the ID until it is not a duplicate
    private static void addItems(Scanner sc, ArrayList<LibraryItem> items) {
        int n = readInt(sc, "Enter number of items: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nItem " + (i + 1));
            int type = readInt(sc, "Type (1-Printed book, 2-Magazine): ", 1, 2);
            String id = readText(sc, "Item ID: ");
            while (findItem(items, id) != null) {
                System.out.println("Item ID already exists!");
                id = readText(sc, "Item ID: ");
            }
            String title = readText(sc, "Title: ");
            String author = readText(sc, "Author: ");
            int days = readInt(sc, "Days borrowed: ", 1, Integer.MAX_VALUE);

            if (type == 1) {
                int pages = readInt(sc, "Pages: ", 1, Integer.MAX_VALUE);
                items.add(new PrintedBook(id, title, author, days, pages));
            } else {
                int issue = readInt(sc, "Issue number: ", 1, Integer.MAX_VALUE);
                items.add(new Magazine(id, title, author, days, issue));
            }
        }
    }

    // Input: item
    // Output: prints the item and its fee
    // Approach: displayInfo() and calculateFee() on a LibraryItem reference
    private static void display(LibraryItem item) {
        item.displayInfo();
        System.out.printf("Fee: %,d VND%n", item.calculateFee());
    }

    // Input: items
    // Output: prints every item and its fee
    // Approach: overload of display(), loop and call display(item)
    private static void display(ArrayList<LibraryItem> items) {
        for (LibraryItem item : items) {
            System.out.println();
            display(item);
        }
    }

    // Input: items
    // Output: total borrowing revenue in VND
    // Approach: loop, add calculateFee()
    private static long totalRevenue(ArrayList<LibraryItem> items) {
        long total = 0;
        for (LibraryItem item : items) {
            total += item.calculateFee();
        }
        return total;
    }

    // Input: items (not empty)
    // Output: item with the highest fee
    // Approach: loop, keep the max calculateFee()
    private static LibraryItem findHighestFee(ArrayList<LibraryItem> items) {
        LibraryItem best = items.get(0);
        for (LibraryItem item : items) {
            if (item.calculateFee() > best.calculateFee()) {
                best = item;
            }
        }
        return best;
    }

    // Input: items (not empty)
    // Output: prints type counts, total revenue, highest-fee item
    // Approach: count with instanceof, then call the helpers above
    private static void printStatistics(ArrayList<LibraryItem> items) {
        int books = 0;
        int magazines = 0;
        for (LibraryItem item : items) {
            if (item instanceof PrintedBook) {
                books++;
            } else if (item instanceof Magazine) {
                magazines++;
            }
        }
        System.out.println("Printed books: " + books);
        System.out.println("Magazines: " + magazines);
        System.out.printf("Total revenue: %,d VND%n", totalRevenue(items));
        System.out.println("\nItem with the highest fee:");
        display(findHighestFee(items));
    }

    // Input: menu choices and item info from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<LibraryItem> items = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add items  2. Display all  3. Search by ID  4. Statistics  0. Exit");
            int choice = readInt(sc, "Choice: ", 0, 4);

            if (choice == 0) {
                break;
            }
            if (choice != 1 && items.isEmpty()) {
                System.out.println("The list is empty.");
                continue;
            }

            switch (choice) {
                case 1:
                    addItems(sc, items);
                    break;
                case 2:
                    display(items);
                    break;
                case 3:
                    LibraryItem item = findItem(items, readText(sc, "Item ID: "));
                    if (item == null) {
                        System.out.println("Item not found.");
                    } else {
                        display(item);
                    }
                    break;
                case 4:
                    printStatistics(items);
                    break;
            }
        }

        sc.close();
    }
}
