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

    // Input: sc, prompt
    // Output: positive number read from keyboard
    // Approach: repeat until the line is a finite number > 0
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value > 0 && value < Double.POSITIVE_INFINITY) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    // Input: deliveries, id
    // Output: delivery with this ID, null if not found
    // Approach: loop, compare IDs ignoring case
    private static Delivery findDelivery(ArrayList<Delivery> deliveries, String id) {
        for (Delivery d : deliveries) {
            if (d.getOrderId().equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    // Input: deliveries, type
    // Output: deliveries of this type
    // Approach: loop, keep deliveries whose getType() matches
    private static ArrayList<Delivery> findByType(ArrayList<Delivery> deliveries, String type) {
        ArrayList<Delivery> result = new ArrayList<>();
        for (Delivery d : deliveries) {
            if (d.getType().equals(type)) {
                result.add(d);
            }
        }
        return result;
    }

    // Input: deliveries, threshold
    // Output: deliveries whose fee exceeds the threshold
    // Approach: loop, keep fee > threshold
    private static ArrayList<Delivery> findAbove(ArrayList<Delivery> deliveries, double threshold) {
        ArrayList<Delivery> result = new ArrayList<>();
        for (Delivery d : deliveries) {
            if (d.calculateShippingFee() > threshold) {
                result.add(d);
            }
        }
        return result;
    }

    // Input: deliveries
    // Output: total shipping revenue in VND
    // Approach: sum calculateShippingFee() on each Delivery reference
    private static double total(ArrayList<Delivery> deliveries) {
        double sum = 0;
        for (Delivery d : deliveries) {
            sum += d.calculateShippingFee();
        }
        return sum;
    }

    // Input: sc, deliveries
    // Output: none, N new deliveries added
    // Approach: read each order, repeat the ID until it is not a duplicate
    private static void addDeliveries(Scanner sc, ArrayList<Delivery> deliveries) {
        int n = readInt(sc, "Enter number of orders: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nOrder " + (i + 1));
            int type = readInt(sc, "Type (1-Standard, 2-Express): ", 1, 2);
            String id = readText(sc, "Order ID: ");
            while (findDelivery(deliveries, id) != null) {
                System.out.println("ID already exists!");
                id = readText(sc, "Order ID: ");
            }
            String name = readText(sc, "Customer name: ");
            double distance = readDouble(sc, "Distance (km): ");
            double weight = readDouble(sc, "Weight (kg): ");

            if (type == 1) {
                deliveries.add(new StandardDelivery(id, name, distance, weight));
            } else {
                deliveries.add(new ExpressDelivery(id, name, distance, weight));
            }
        }
    }

    // Input: delivery
    // Output: prints the order and its fee, or Not found.
    // Approach: displayInfo() and calculateShippingFee() on a Delivery reference
    private static void display(Delivery delivery) {
        if (delivery == null) {
            System.out.println("Not found.");
            return;
        }
        delivery.displayInfo();
        System.out.printf("Shipping fee: %,.2f VND%n", delivery.calculateShippingFee());
    }

    // Input: deliveries
    // Output: prints every order and fee, or Not found. if empty
    // Approach: overload of display(), loop and call display(delivery)
    private static void display(ArrayList<Delivery> deliveries) {
        if (deliveries.isEmpty()) {
            System.out.println("Not found.");
        }
        for (Delivery d : deliveries) {
            System.out.println();
            display(d);
        }
    }

    // Input: deliveries (not empty)
    // Output: prints count of each type, total revenue, created counter
    // Approach: findByType(...).size() for counts, total(), Delivery.getCreatedCount()
    private static void printStatistics(ArrayList<Delivery> deliveries) {
        System.out.println("Standard orders: " + findByType(deliveries, "Standard").size());
        System.out.println("Express orders: " + findByType(deliveries, "Express").size());
        System.out.printf("Total shipping revenue: %,.2f VND%n", total(deliveries));
        System.out.println("Delivery objects created: " + Delivery.getCreatedCount());
        System.out.println("(static: one counter shared by the Delivery class, not a copy in each object)");
    }

    // Input: none
    // Output: prints the menu
    // Approach: one line per option, 0 exits
    private static void printMenu() {
        System.out.println("\n=== Delivery Management ===");
        System.out.println("1. Add orders");
        System.out.println("2. Display all");
        System.out.println("3. Find by ID");
        System.out.println("4. Statistics");
        System.out.println("5. Orders with fee above threshold");
        System.out.println("0. Exit");
    }

    // Input: menu choices and data from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Delivery> deliveries = new ArrayList<>();

        while (true) {
            printMenu();
            int choice = readInt(sc, "Choice: ", 0, 5);

            if (choice == 0) {
                break;
            }
            if (choice != 1 && deliveries.isEmpty()) {
                System.out.println("The list is empty.");
                continue;
            }

            switch (choice) {
                case 1:
                    addDeliveries(sc, deliveries);
                    break;
                case 2:
                    display(deliveries);
                    break;
                case 3:
                    display(findDelivery(deliveries, readText(sc, "Order ID: ")));
                    break;
                case 4:
                    printStatistics(deliveries);
                    break;
                case 5:
                    display(findAbove(deliveries, readDouble(sc, "Fee threshold (VND): ")));
                    break;
            }
        }

        sc.close();
    }
}
