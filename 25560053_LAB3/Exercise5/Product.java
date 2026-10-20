public abstract class Product {
    private static int nextId = 1;
    private String productId;
    private String type;
    private int volume;

    // Input: type, volume (ml, > 0)
    // Output: new product with an auto ID
    // Approach: unknown type or volume <= 0 -> exception, ID from static counter
    public Product(String type, int volume) {
        this.type = type;
        if (getUnitPrice() == 0) {
            throw new IllegalArgumentException("Unknown product type: " + type);
        }
        if (volume <= 0) {
            throw new IllegalArgumentException("Volume must be greater than 0 ml");
        }
        this.volume = volume;
        this.productId = "P" + nextId++;
    }

    // Input: none
    // Output: product type
    // Approach: return type
    public String getType() {
        return type;
    }

    // Input: none
    // Output: price per ml in VND, 0 if the type is unknown
    // Approach: implemented by subclasses
    public abstract long getUnitPrice();

    // Input: none
    // Output: product price in VND
    // Approach: volume x unit price
    public long getPrice() {
        return volume * getUnitPrice();
    }

    // Input: none
    // Output: one line with ID, type, volume, price
    // Approach: join all fields
    @Override
    public String toString() {
        return productId + " - " + type + " - " + volume + " ml - " + getPrice() + " VND";
    }
}
