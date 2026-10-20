public class Shampoo extends Product {
    public static final String DRY_HAIR = "Dry Hair Shampoo";
    public static final String OILY_HAIR_1 = "Oily Hair Shampoo - Standard 1";
    public static final String OILY_HAIR_2 = "Oily Hair Shampoo - Standard 2";

    // Input: type (DRY_HAIR, OILY_HAIR_1 or OILY_HAIR_2), volume
    // Output: new shampoo
    // Approach: super(type, volume)
    public Shampoo(String type, int volume) {
        super(type, volume);
    }

    // Input: none
    // Output: true if this is an oily hair shampoo
    // Approach: compare type with both oily standards
    public boolean isOilyHair() {
        return getType().equals(OILY_HAIR_1) || getType().equals(OILY_HAIR_2);
    }

    // Input: none
    // Output: price per ml in VND
    // Approach: Dry 20,000, Oily Std 1 30,000, Oily Std 2 40,000
    @Override
    public long getUnitPrice() {
        switch (getType()) {
            case DRY_HAIR:
                return 20000;
            case OILY_HAIR_1:
                return 30000;
            case OILY_HAIR_2:
                return 40000;
            default:
                return 0;
        }
    }
}
