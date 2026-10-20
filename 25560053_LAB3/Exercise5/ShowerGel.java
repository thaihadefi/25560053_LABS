public class ShowerGel extends Product {
    public static final String DRY_SKIN = "Dry Skin Shower Gel";
    public static final String OILY_SKIN = "Oily Skin Shower Gel";

    // Input: type (DRY_SKIN or OILY_SKIN), volume
    // Output: new shower gel
    // Approach: super(type, volume)
    public ShowerGel(String type, int volume) {
        super(type, volume);
    }

    // Input: none
    // Output: price per ml in VND
    // Approach: Dry skin 20,000, Oily skin 40,000
    @Override
    public long getUnitPrice() {
        switch (getType()) {
            case DRY_SKIN:
                return 20000;
            case OILY_SKIN:
                return 40000;
            default:
                return 0;
        }
    }
}
