public class StandardDelivery extends Delivery {
    private static final double BASE_FEE = 15000;
    private static final double FEE_PER_KM = 3000;
    private static final double FEE_PER_KG = 2000;

    // Input: orderId, customerName, distance, weight
    // Output: new standard delivery
    // Approach: super(...) with type Standard
    public StandardDelivery(String orderId, String customerName, double distance, double weight) {
        super("Standard", orderId, customerName, distance, weight);
    }

    @Override
    // Input: none
    // Output: shipping fee in VND
    // Approach: 15,000 + 3,000 x distance + 2,000 x weight
    public double calculateShippingFee() {
        return BASE_FEE + FEE_PER_KM * getDistance() + FEE_PER_KG * getWeight();
    }
}
