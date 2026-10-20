public class Guest extends Person {
    private String cccd;
    private Room room;

    // Input: cccd (12 digits), fullName, age, room
    // Output: new guest
    // Approach: invalid CCCD -> exception, super(fullName, age)
    public Guest(String cccd, String fullName, int age, Room room) {
        super(fullName, age);
        if (!isValidCccd(cccd)) {
            throw new IllegalArgumentException("CCCD must have exactly 12 digits");
        }
        this.cccd = cccd;
        this.room = room;
    }

    // Input: cccd
    // Output: true if cccd has exactly 12 digits
    // Approach: regex \d{12}
    public static boolean isValidCccd(String cccd) {
        return cccd != null && cccd.matches("\\d{12}");
    }

    // Input: none
    // Output: citizen ID card number
    // Approach: return cccd
    public String getCccd() {
        return cccd;
    }

    // Input: none
    // Output: rental cost of the guest's room
    // Approach: room.calculateCost()
    public long getRentalCost() {
        return room.calculateCost();
    }

    // Input: none
    // Output: text with CCCD, person info and room
    // Approach: CCCD, super.toString(), then room
    @Override
    public String toString() {
        return "CCCD: " + cccd + "\n" + super.toString() + "\n" + room;
    }
}
