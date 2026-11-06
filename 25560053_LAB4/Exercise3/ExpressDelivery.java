public class ExpressDelivery extends Delivery {
    private static final double BASE_FEE = 30000;
    private static final double FEE_PER_KM = 5000;
    private static final double FEE_PER_KG = 4000;

    // Input: orderId, customerName, distance, weight
    // Output: new express delivery
    // Approach: super(...) with type Express
    public ExpressDelivery(String orderId, String customerName, double distance, double weight) {
        super("Express", orderId, customerName, distance, weight);
    }

    @Override
    // Input: none
    // Output: shipping fee in VND
    // Approach: 30,000 + 5,000 x distance + 4,000 x weight
    public double calculateShippingFee() {
        return BASE_FEE + FEE_PER_KM * getDistance() + FEE_PER_KG * getWeight();
    }
}
