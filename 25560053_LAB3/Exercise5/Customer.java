public class Customer {
    private String customerId;
    private String fullName;
    private String address;
    private String phone;

    // Input: customerId, fullName, address, phone (Vietnamese mobile number)
    // Output: new customer
    // Approach: invalid phone -> exception, store all fields
    public Customer(String customerId, String fullName, String address, String phone) {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid Vietnamese phone number");
        }
        this.customerId = customerId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    // Input: phone
    // Output: true if phone is a Vietnamese mobile number (0 or +84, then 3/5/7/8/9, then 8 digits)
    // Approach: regex (0|\+84)[35789]\d{8}
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("(0|\\+84)[35789]\\d{8}");
    }

    // Input: none
    // Output: one line with all customer info
    // Approach: join fields
    @Override
    public String toString() {
        return customerId + " - " + fullName + " - " + address + " - " + phone;
    }
}
