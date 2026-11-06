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
    // Output: number > 0 read from keyboard
    // Approach: repeat until the line is a positive number
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
    // Output: delivery with this order ID, null if not found
    // Approach: loop, compare IDs ignoring case
    private static Delivery findDelivery(ArrayList<Delivery> deliveries, String id) {
        for (Delivery d : deliveries) {
            if (d.getOrderId().equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    // Input: sc, deliveries
    // Output: none, N new orders added
    // Approach: read each order, repeat the ID until it is not a duplicate
    private static void addDeliveries(Scanner sc, ArrayList<Delivery> deliveries) {
        int n = readInt(sc, "Enter number of orders: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nOrder " + (i + 1));
            int type = readInt(sc, "Type (1-Standard, 2-Express): ", 1, 2);
            String id = readText(sc, "Order ID: ");
            while (findDelivery(deliveries, id) != null) {
                System.out.println("Order ID already exists!");
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
    // Output: prints the order and its shipping fee
    // Approach: displayInfo() and calculateShippingFee() on a Delivery reference
    private static void display(Delivery delivery) {
        delivery.displayInfo();
        System.out.printf("Shipping fee: %,.2f VND%n", delivery.calculateShippingFee());
    }

    // Input: deliveries
    // Output: prints every order
    // Approach: loop and call display()
    private static void printOrders(ArrayList<Delivery> deliveries) {
        for (Delivery d : deliveries) {
            System.out.println();
            display(d);
        }
    }

    // Input: deliveries, threshold
    // Output: prints orders whose fee exceeds the threshold, or a message
    // Approach: overload of printOrders(), filter with fee > threshold
    private static void printOrders(ArrayList<Delivery> deliveries, double threshold) {
        ArrayList<Delivery> result = new ArrayList<>();
        for (Delivery d : deliveries) {
            if (d.calculateShippingFee() > threshold) {
                result.add(d);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No order exceeds this fee.");
        } else {
            printOrders(result);
        }
    }

    // Input: deliveries
    // Output: total shipping revenue in VND
    // Approach: loop, add calculateShippingFee()
    private static double totalRevenue(ArrayList<Delivery> deliveries) {
        double total = 0;
        for (Delivery d : deliveries) {
            total += d.calculateShippingFee();
        }
        return total;
    }

    // Input: deliveries (not empty)
    // Output: prints type counts, revenue and the static created counter
    // Approach: count with instanceof, read the counter through the class name
    private static void printStatistics(ArrayList<Delivery> deliveries) {
        int standard = 0;
        int express = 0;
        for (Delivery d : deliveries) {
            if (d instanceof StandardDelivery) {
                standard++;
            } else if (d instanceof ExpressDelivery) {
                express++;
            }
        }
        System.out.println("Standard orders: " + standard);
        System.out.println("Express orders: " + express);
        System.out.printf("Total shipping revenue: %,.2f VND%n", totalRevenue(deliveries));
        System.out.println("Delivery objects created: " + Delivery.getCreatedCount());
        System.out.println("(static: one counter shared by the Delivery class, not a copy in each object)");
    }

    // Input: menu choices and order info from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Delivery> deliveries = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add orders  2. Display all  3. Search by order ID  "
                    + "4. Statistics  5. Orders above a fee  0. Exit");
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
                    printOrders(deliveries);
                    break;
                case 3:
                    Delivery delivery = findDelivery(deliveries, readText(sc, "Order ID: "));
                    if (delivery == null) {
                        System.out.println("Order not found.");
                    } else {
                        display(delivery);
                    }
                    break;
                case 4:
                    printStatistics(deliveries);
                    break;
                case 5:
                    printOrders(deliveries, readDouble(sc, "Fee threshold (VND): "));
                    break;
            }
        }

        sc.close();
    }
}
