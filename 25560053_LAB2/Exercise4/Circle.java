public class Circle {
    private static final double EPS = 1e-9;

    // Attributes
    private double centerX;
    private double centerY;
    private double radius;

    // No-argument constructor
    // Input: none
    // Output: unit circle at (0, 0)
    // Purpose: default circle
    // Approach: this(0, 0, 1)
    public Circle() {
        this(0, 0, 1);
    }

    // Parameterized constructor
    // Input: centerX, centerY, radius
    // Output: new circle
    // Purpose: circle with given values
    // Approach: radius <= 0 -> 1
    public Circle(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius > 0 ? radius : 1;
    }

    // Methods
    // Input: none
    // Output: area
    // Purpose: get area
    // Approach: PI * r^2
    public double area() {
        return Math.PI * radius * radius;
    }

    // Input: none
    // Output: perimeter
    // Purpose: get perimeter
    // Approach: 2 * PI * r
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    // Input: x, y
    // Output: true if point is inside or on circle
    // Purpose: check a point
    // Approach: dx^2 + dy^2 <= r^2 (+ EPS for rounding)
    public boolean contains(double x, double y) {
        double dx = x - centerX;
        double dy = y - centerY;
        return dx * dx + dy * dy <= radius * radius + EPS;
    }

    // Input: none
    // Output: prints C(a, b, r)
    // Purpose: show circle
    // Approach: print 3 fields
    public void displayInfo() {
        System.out.println("C(" + centerX + ", " + centerY + ", " + radius + ")");
    }
}
