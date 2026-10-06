public class Rectangle {
    // Attributes
    private double width;
    private double height;

    // No-argument constructor
    // Input: none
    // Output: 1 x 1 rectangle
    // Purpose: default rectangle
    // Approach: this(1, 1)
    public Rectangle() {
        this(1, 1);
    }

    // Parameterized constructor
    // Input: width, height
    // Output: new rectangle
    // Purpose: rectangle with given size
    // Approach: size <= 0 -> 1
    public Rectangle(double width, double height) {
        this.width = width > 0 ? width : 1;
        this.height = height > 0 ? height : 1;
    }

    // Methods
    // Input: none
    // Output: area
    // Purpose: get area
    // Approach: width * height
    public double area() {
        return width * height;
    }

    // Input: none
    // Output: perimeter
    // Purpose: get perimeter
    // Approach: 2 * (width + height)
    public double perimeter() {
        return 2 * (width + height);
    }

    // Input: none
    // Output: prints size
    // Purpose: show rectangle
    // Approach: print width, height
    public void displayInfo() {
        System.out.println("Rectangle " + width + " x " + height);
    }
}
