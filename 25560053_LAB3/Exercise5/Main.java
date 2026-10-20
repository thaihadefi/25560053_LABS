import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "order_list.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

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

    // Input: sc
    // Output: volume in ml, at least 1
    // Approach: readInt with min 1
    private static int readVolume(Scanner sc) {
        return readInt(sc, "  Volume (ml): ", 1, Integer.MAX_VALUE);
    }

    // Input: sc
    // Output: valid Vietnamese mobile number
    // Approach: repeat until Customer.isValidPhone()
    private static String readPhone(Scanner sc) {
        while (true) {
            String phone = readText(sc, "Phone: ");
            if (Customer.isValidPhone(phone)) {
                return phone;
            }
            System.out.println("Invalid Vietnamese phone number!");
        }
    }

    // Input: sc
    // Output: valid date (dd/MM/yyyy), today if empty
    // Approach: strict LocalDate.parse, repeat on DateTimeParseException
    private static LocalDate readDate(Scanner sc) {
        while (true) {
            System.out.print("Invoice date (dd/MM/yyyy, empty = today): ");
            String text = sc.nextLine().trim();
            if (text.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(text, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date!");
            }
        }
    }

    // Input: sc, required (true = Rose only, no "none" option)
    // Output: perfume, null if none chosen
    // Approach: menu of types, then read volume
    private static Perfume readPerfume(Scanner sc, boolean required) {
        int choice;
        if (required) {
            System.out.println("Rose Perfume (required)");
            choice = 1;
        } else {
            choice = readInt(sc, "Perfume (0 = none, 1 = Rose, 2 = Chamomile): ", 0, 2);
        }
        if (choice == 0) {
            return null;
        }
        String type = choice == 1 ? Perfume.ROSE : Perfume.CHAMOMILE;
        return new Perfume(type, readVolume(sc));
    }

    // Input: sc, required (true = oily hair only, no "none" option)
    // Output: shampoo, null if none chosen
    // Approach: menu of types, then read volume
    private static Shampoo readShampoo(Scanner sc, boolean required) {
        String type;
        if (required) {
            int choice = readInt(sc, "Oily Hair Shampoo (1-Standard 1, 2-Standard 2): ", 1, 2);
            type = choice == 1 ? Shampoo.OILY_HAIR_1 : Shampoo.OILY_HAIR_2;
        } else {
            int choice = readInt(sc, "Shampoo (0-None, 1-Dry Hair, 2-Oily Std 1, 3-Oily Std 2): ", 0, 3);
            if (choice == 0) {
                return null;
            }
            type = choice == 1 ? Shampoo.DRY_HAIR : choice == 2 ? Shampoo.OILY_HAIR_1 : Shampoo.OILY_HAIR_2;
        }
        return new Shampoo(type, readVolume(sc));
    }

    // Input: sc
    // Output: shower gel, null if none chosen
    // Approach: menu of types, then read volume
    private static ShowerGel readShowerGel(Scanner sc) {
        int choice = readInt(sc, "Shower Gel (0 = none, 1 = Dry Skin, 2 = Oily Skin): ", 0, 2);
        if (choice == 0) {
            return null;
        }
        String type = choice == 1 ? ShowerGel.DRY_SKIN : ShowerGel.OILY_SKIN;
        return new ShowerGel(type, readVolume(sc));
    }

    // Input: sc
    // Output: Romantic or Fresh-Air package
    // Approach: Romantic needs Rose, Fresh-Air needs oily shampoo
    private static ProductPackage readPackage(Scanner sc) {
        int type = readInt(sc, "Package (1 = Romantic, 2 = Fresh-Air): ", 1, 2);
        if (type == 1) {
            Perfume perfume = readPerfume(sc, true);
            Shampoo shampoo = readShampoo(sc, false);
            ShowerGel gel = readShowerGel(sc);
            return new RomanticPackage(perfume, shampoo, gel);
        }
        Perfume perfume = readPerfume(sc, false);
        Shampoo shampoo = readShampoo(sc, true);
        ShowerGel gel = readShowerGel(sc);
        return new FreshAirPackage(perfume, shampoo, gel);
    }

    // Input: orders, orderId
    // Output: true if an order with this ID is already in the list
    // Approach: loop, compare getOrderId()
    private static boolean hasOrder(ArrayList<Order> orders, String orderId) {
        for (Order o : orders) {
            if (o.getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    // Input: orders, fileName
    // Output: true if saved, orders written to the file
    // Approach: BufferedWriter in try-with-resources, catch IOException
    private static boolean saveOrders(ArrayList<Order> orders, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order o : orders) {
                writer.write(o.toString());
                writer.newLine();
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Cannot save file: " + e.getMessage());
            return false;
        }
    }

    // Input: N, then customer, date and package of each order from keyboard
    // Output: all orders on screen, then saved to order_list.txt
    // Approach: read into ArrayList<Order>, print each, then saveOrders()
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Order> orders = new ArrayList<>();

        int n = readInt(sc, "Enter number of orders: ", 1, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            System.out.println("\nOrder " + (i + 1));
            String orderId = readText(sc, "Order ID: ");
            while (hasOrder(orders, orderId)) {
                System.out.println("Order ID already exists!");
                orderId = readText(sc, "Order ID: ");
            }
            String customerId = readText(sc, "Customer ID: ");
            String name = readText(sc, "Full name: ");
            String address = readText(sc, "Address: ");
            String phone = readPhone(sc);
            LocalDate date = readDate(sc);
            ProductPackage pkg = readPackage(sc);

            orders.add(new Order(orderId, new Customer(customerId, name, address, phone), date, pkg));
        }

        System.out.println("\nOrder list:");
        for (Order o : orders) {
            System.out.println(o);
            System.out.println();
        }

        if (saveOrders(orders, FILE_NAME)) {
            System.out.println("Saved to " + FILE_NAME);
        }

        sc.close();
    }
}
