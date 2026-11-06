public abstract class Delivery {
    private static int createdCount = 0;
    private String type;
    private String orderId;
    private String customerName;
    private double distance;
    private double weight;

    // Input: type, orderId, customerName, distance (km), weight (kg)
    // Output: new delivery, created counter increased
    // Approach: empty ID -> exception, numbers via setters, count last so failed objects are not counted
    public Delivery(String type, String orderId, String customerName, double distance, double weight) {
        if (orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID must not be empty");
        }
        this.type = type;
        this.orderId = orderId;
        this.customerName = customerName;
        setDistance(distance);
        setWeight(weight);
        createdCount++;
    }

    // Input: none
    // Output: type name
    // Approach: return type
    public String getType() {
        return type;
    }

    // Input: none
    // Output: number of Delivery objects created successfully
    // Approach: static because the counter is shared by the class, not owned by one object
    public static int getCreatedCount() {
        return createdCount;
    }

    // Input: none
    // Output: order ID
    // Approach: return orderId
    public String getOrderId() {
        return orderId;
    }

    // Input: none
    // Output: distance in km
    // Approach: return distance
    public double getDistance() {
        return distance;
    }

    // Input: distance
    // Output: none, distance updated
    // Approach: <= 0 -> exception, else update
    public void setDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        this.distance = distance;
    }

    // Input: none
    // Output: weight in kg
    // Approach: return weight
    public double getWeight() {
        return weight;
    }

    // Input: weight
    // Output: none, weight updated
    // Approach: <= 0 -> exception, else update
    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.weight = weight;
    }

    // Input: none
    // Output: total shipping fee in VND
    // Approach: implemented by subclasses
    public abstract double calculateShippingFee();

    // Input: none
    // Output: prints common info with units
    // Approach: print type, then each field on one line; subclasses add their own
    public void displayInfo() {
        System.out.println("Type: " + type);
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Weight: " + weight + " kg");
    }
}
