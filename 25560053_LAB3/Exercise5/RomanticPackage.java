public class RomanticPackage extends ProductPackage {
    // Input: perfume (required, Rose), shampoo and showerGel (optional, null = none)
    // Output: new Romantic package
    // Approach: super(...), missing or non-Rose perfume -> exception
    public RomanticPackage(Perfume perfume, Shampoo shampoo, ShowerGel showerGel) {
        super(perfume, shampoo, showerGel);
        if (perfume == null || !perfume.getType().equals(Perfume.ROSE)) {
            throw new IllegalArgumentException("Romantic Package must include Rose Perfume");
        }
    }

    // Input: none
    // Output: "Romantic"
    // Approach: return constant
    @Override
    public String getName() {
        return "Romantic";
    }
}
