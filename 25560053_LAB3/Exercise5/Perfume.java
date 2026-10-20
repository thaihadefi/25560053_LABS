public class Perfume extends Product {
    public static final String ROSE = "Rose Perfume";
    public static final String CHAMOMILE = "Chamomile Perfume";

    // Input: type (ROSE or CHAMOMILE), volume
    // Output: new perfume
    // Approach: super(type, volume)
    public Perfume(String type, int volume) {
        super(type, volume);
    }

    // Input: none
    // Output: price per ml in VND
    // Approach: Rose 20,000, Chamomile 15,000
    @Override
    public long getUnitPrice() {
        switch (getType()) {
            case ROSE:
                return 20000;
            case CHAMOMILE:
                return 15000;
            default:
                return 0;
        }
    }
}
