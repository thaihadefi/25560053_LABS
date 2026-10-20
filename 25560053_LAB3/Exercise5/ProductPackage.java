public abstract class ProductPackage {
    private Perfume perfume;
    private Shampoo shampoo;
    private ShowerGel showerGel;

    // Input: perfume, shampoo, showerGel (null = not included)
    // Output: new package
    // Approach: store the three products
    public ProductPackage(Perfume perfume, Shampoo shampoo, ShowerGel showerGel) {
        this.perfume = perfume;
        this.shampoo = shampoo;
        this.showerGel = showerGel;
    }

    // Input: none
    // Output: package name
    // Approach: implemented by subclasses
    public abstract String getName();

    // Input: none
    // Output: package price in VND
    // Approach: add getPrice() of each product that is not null
    public long getPrice() {
        long total = 0;
        if (perfume != null) {
            total += perfume.getPrice();
        }
        if (shampoo != null) {
            total += shampoo.getPrice();
        }
        if (showerGel != null) {
            total += showerGel.getPrice();
        }
        return total;
    }

    // Input: none
    // Output: package name, one line per product, package price
    // Approach: join name, each product that is not null, then price
    @Override
    public String toString() {
        String result = "Package: " + getName() + "\n";
        if (perfume != null) {
            result += "    " + perfume + "\n";
        }
        if (shampoo != null) {
            result += "    " + shampoo + "\n";
        }
        if (showerGel != null) {
            result += "    " + showerGel + "\n";
        }
        result += "  Package price: " + getPrice() + " VND";
        return result;
    }
}
