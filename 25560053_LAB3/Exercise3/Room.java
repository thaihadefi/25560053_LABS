public class Room {
    private String roomType;
    private int rentalDays;

    // Input: roomType (VIP1, VIP2, VIP3), rentalDays
    // Output: new room rental
    // Approach: unknown type -> exception, rentalDays via setter
    public Room(String roomType, int rentalDays) {
        if (!isValidType(roomType)) {
            throw new IllegalArgumentException("Unknown room type: " + roomType);
        }
        this.roomType = roomType.toUpperCase();
        setRentalDays(rentalDays);
    }

    // Input: roomType
    // Output: daily price in VND, 0 if unknown
    // Approach: switch on the type
    public static long getPricePerDay(String roomType) {
        switch (roomType.toUpperCase()) {
            case "VIP1":
                return 900000;
            case "VIP2":
                return 700000;
            case "VIP3":
                return 500000;
            default:
                return 0;
        }
    }

    // Input: roomType
    // Output: true if the type is in the price table
    // Approach: daily price > 0
    public static boolean isValidType(String roomType) {
        return getPricePerDay(roomType) > 0;
    }

    // Input: rentalDays
    // Output: none, days updated if valid
    // Approach: >= 1 -> update, else print error
    public void setRentalDays(int rentalDays) {
        if (rentalDays >= 1) {
            this.rentalDays = rentalDays;
        } else {
            System.out.println("Rental days must be at least 1!");
        }
    }

    // Input: none
    // Output: rental cost in VND
    // Approach: daily price x rental days
    public long calculateCost() {
        return getPricePerDay(roomType) * rentalDays;
    }

    // Input: none
    // Output: text with room type, days and cost
    // Approach: join fields, cost from calculateCost()
    @Override
    public String toString() {
        return "Room type: " + roomType + "\n"
                + "Rental days: " + rentalDays + "\n"
                + "Rental cost: " + calculateCost() + " VND";
    }
}
