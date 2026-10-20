import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String orderId;
    private Customer customer;
    private LocalDate invoiceDate;
    private ProductPackage productPackage;

    // Input: orderId, customer, invoiceDate, productPackage
    // Output: new order
    // Approach: store all fields
    public Order(String orderId, Customer customer, LocalDate invoiceDate, ProductPackage productPackage) {
        this.orderId = orderId;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
        this.productPackage = productPackage;
    }

    // Input: none
    // Output: order ID
    // Approach: return orderId
    public String getOrderId() {
        return orderId;
    }

    // Input: none
    // Output: order price in VND
    // Approach: price of its package
    public long getPrice() {
        return productPackage.getPrice();
    }

    // Input: none
    // Output: multi-line text with all order info
    // Approach: join order fields, customer and package toString()
    @Override
    public String toString() {
        return "Order " + orderId + " - Date: " + invoiceDate.format(DATE_FORMAT) + "\n"
                + "  Customer: " + customer + "\n"
                + "  " + productPackage + "\n"
                + "  Order price: " + getPrice() + " VND";
    }
}
