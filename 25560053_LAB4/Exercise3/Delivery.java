public abstract class Delivery {
    private static int createdCount = 0;
    private String type;
    private String orderId;
    private String customerName;
    private double distance;
    private double weight;

    // Input: type, orderId, customerName, distance, weight
    // Output: new delivery, created counter increased
    // Approach: empty ID -> exception, numbers via setters, count last so failed objects are not counted
    public Delivery(String type, String orderId, String customerName, double distance, double weight) {
        if (orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be empty");
        }
        this.type = type;
        this.orderId = orderId;
        this.customerName = customerName;
        setDistance(distance);
        setWeight(weight);
        createdCount++;
    }

    // Input: none
    // Output: number of Delivery objects created successfully
    // Approach: static because the counter is shared by the class, not owned by one object
    public static int getCreatedCount() {
        return createdCount;
    }

    // Input: none
    // Output: type
    // Approach: return type
    public String getType() {
        return type;
    }

    // Input: none
    // Output: order ID
    // Approach: return orderId
    public String getOrderId() {
        return orderId;
    }

    // Input: none
    // Output: customer name
    // Approach: return customerName
    public String getCustomerName() {
        return customerName;
    }

    // Input: none
    // Output: distance
    // Approach: return distance
    public double getDistance() {
        return distance;
    }

    // Input: distance
    // Output: none, distance updated
    // Approach: <= 0 -> exception, else update
    public void setDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero");
        }
        this.distance = distance;
    }

    // Input: none
    // Output: weight
    // Approach: return weight
    public double getWeight() {
        return weight;
    }

    // Input: weight
    // Output: none, weight updated
    // Approach: <= 0 -> exception, else update
    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }
        this.weight = weight;
    }

    // Input: none
    // Output: shipping fee in VND
    // Approach: implemented by subclasses
    public abstract double calculateShippingFee();

    // Input: none
    // Output: prints ID, type, customer, distance (km), weight (kg)
    // Approach: one field per line with units
    public void displayInfo() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Delivery Type: " + type);
        System.out.println("Customer Name: " + customerName);
        System.out.printf("Distance: %.2f km%n", distance);
        System.out.printf("Weight: %.2f kg%n", weight);
    }
}
