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
        for (LibraryItem i : items) {
            if (i.getItemId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }

    // Input: items, type
    // Output: items of this type
    // Approach: loop, keep items whose getType() matches
    private static ArrayList<LibraryItem> findByType(ArrayList<LibraryItem> items, String type) {
        ArrayList<LibraryItem> result = new ArrayList<>();
        for (LibraryItem i : items) {
            if (i.getType().equals(type)) {
                result.add(i);
            }
        }
        return result;
    }

    // Input: items
    // Output: item with the highest fee, null if empty
    // Approach: keep the best so far, replace when a fee is higher
    private static LibraryItem findMax(ArrayList<LibraryItem> items) {
        if (items.isEmpty()) {
            return null;
        }
        LibraryItem best = items.get(0);
        for (LibraryItem i : items) {
            if (i.calculateFee() > best.calculateFee()) {
                best = i;
            }
        }
        return best;
    }

    // Input: items
    // Output: total borrowing revenue in VND
    // Approach: sum calculateFee() on each LibraryItem reference
    private static long total(ArrayList<LibraryItem> items) {
        long sum = 0;
        for (LibraryItem i : items) {
            sum += i.calculateFee();
        }
        return sum;
    }

    // Input: sc, items
    // Output: none, N new items added
    // Approach: read each item, repeat the ID until it is not a duplicate
    private static void addItems(Scanner sc, ArrayList<LibraryItem> items) {
        int n = readInt(sc, "Enter number of items: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nItem " + (i + 1));
            int type = readInt(sc, "Type (1-Printed Book, 2-Magazine): ", 1, 2);
            String id = readText(sc, "Item ID: ");
            while (findItem(items, id) != null) {
                System.out.println("ID already exists!");
                id = readText(sc, "Item ID: ");
            }
            String title = readText(sc, "Title: ");
            String author = readText(sc, "Author: ");
            int days = readInt(sc, "Days borrowed: ", 1, Integer.MAX_VALUE);

            if (type == 1) {
                int pages = readInt(sc, "Number of pages: ", 1, Integer.MAX_VALUE);
                items.add(new PrintedBook(id, title, author, days, pages));
            } else {
                int issue = readInt(sc, "Issue number: ", 1, Integer.MAX_VALUE);
                items.add(new Magazine(id, title, author, days, issue));
            }
        }
    }

    // Input: item
    // Output: prints the item and its fee, or Not found.
    // Approach: displayInfo() and calculateFee() on a LibraryItem reference
    private static void display(LibraryItem item) {
        if (item == null) {
            System.out.println("Not found.");
            return;
        }
        item.displayInfo();
        System.out.printf("Fee: %,d VND%n", item.calculateFee());
    }

    // Input: items
    // Output: prints every item and its fee, or Not found. if empty
    // Approach: overload of display(), loop and call display(item)
    private static void display(ArrayList<LibraryItem> items) {
        if (items.isEmpty()) {
            System.out.println("Not found.");
        }
        for (LibraryItem i : items) {
            System.out.println();
            display(i);
        }
    }

    // Input: items (not empty)
    // Output: prints count of each type, total revenue, highest fee item
    // Approach: findByType(...).size() for counts, total(), findMax()
    private static void printStatistics(ArrayList<LibraryItem> items) {
        System.out.println("Printed books: " + findByType(items, "Printed Book").size());
        System.out.println("Magazines: " + findByType(items, "Magazine").size());
        System.out.printf("Total revenue: %,d VND%n", total(items));
        System.out.println("\nHighest fee:");
        display(findMax(items));
    }

    // Input: none
    // Output: prints the menu
    // Approach: one line per option, 0 exits
    private static void printMenu() {
        System.out.println("\n=== Library Management ===");
        System.out.println("1. Add items");
        System.out.println("2. Display all");
        System.out.println("3. Find by ID");
        System.out.println("4. Statistics");
        System.out.println("0. Exit");
    }

    // Input: menu choices and data from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<LibraryItem> items = new ArrayList<>();

        while (true) {
            printMenu();
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
                    display(findItem(items, readText(sc, "Item ID: ")));
                    break;
                case 4:
                    printStatistics(items);
                    break;
            }
        }

        sc.close();
    }
}
