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
    // Output: CCCD with exactly 12 digits
    // Approach: repeat until Guest.isValidCccd()
    private static String readCccd(Scanner sc, String prompt) {
        while (true) {
            String cccd = readText(sc, prompt);
            if (Guest.isValidCccd(cccd)) {
                return cccd;
            }
            System.out.println("CCCD must have exactly 12 digits!");
        }
    }

    // Input: sc
    // Output: room type in the price table
    // Approach: repeat until Room.isValidType()
    private static String readRoomType(Scanner sc) {
        while (true) {
            String type = readText(sc, "Room type (VIP1/VIP2/VIP3): ");
            if (Room.isValidType(type)) {
                return type;
            }
            System.out.println("Unknown room type!");
        }
    }

    // Input: menu choices and guest/staff info from keyboard
    // Output: result of each action, hotel list
    // Approach: menu loop: add, remove guest, add staff, show all
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n1. Add guest  2. Remove guest  3. Add staff  4. Show all  0. Exit");
            int choice = readInt(sc, "Choice: ", 0, 4);

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                String cccd = readCccd(sc, "CCCD (12 digits): ");
                if (hotel.hasGuest(cccd)) {
                    System.out.println("A guest with this CCCD is already staying.");
                    continue;
                }
                String name = readText(sc, "Full name: ");
                int age = readInt(sc, "Age: ", 1, Person.MAX_AGE);
                String type = readRoomType(sc);
                int days = readInt(sc, "Rental days: ", 1, Integer.MAX_VALUE);

                Guest guest = new Guest(cccd, name, age, new Room(type, days));
                hotel.addGuest(guest);
                System.out.println("Rental cost: " + guest.getRentalCost() + " VND");
            } else if (choice == 2) {
                String cccd = readCccd(sc, "CCCD of guest to remove: ");
                System.out.println(hotel.removeGuest(cccd) ? "Guest removed." : "Guest not found.");
            } else if (choice == 3) {
                String license = readText(sc, "License ID: ");
                String empId = readText(sc, "Employee ID: ");
                if (hotel.hasStaff(empId)) {
                    System.out.println("A staff member with this employee ID already exists.");
                    continue;
                }
                String name = readText(sc, "Full name: ");
                int age = readInt(sc, "Age: ", 1, Person.MAX_AGE);
                hotel.addStaff(new Staff(license, empId, name, age));
                System.out.println("Staff added.");
            } else {
                System.out.println(hotel);
            }
        }

        sc.close();
    }
}
