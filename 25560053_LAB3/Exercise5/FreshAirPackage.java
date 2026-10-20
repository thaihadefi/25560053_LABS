public class FreshAirPackage extends ProductPackage {
    // Input: shampoo (required, oily hair), perfume and showerGel (optional, null = none)
    // Output: new Fresh-Air package
    // Approach: super(...), missing or non-oily shampoo -> exception
    public FreshAirPackage(Perfume perfume, Shampoo shampoo, ShowerGel showerGel) {
        super(perfume, shampoo, showerGel);
        if (shampoo == null || !shampoo.isOilyHair()) {
            throw new IllegalArgumentException("Fresh-Air Package must include Oily Hair Shampoo");
        }
    }

    // Input: none
    // Output: "Fresh-Air"
    // Approach: return constant
    @Override
    public String getName() {
        return "Fresh-Air";
    }
}
