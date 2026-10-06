public class Main {
    // Input: none
    // Output: area, perimeter, contains() for each circle
    // Purpose: test Circle
    // Approach: 3 circles x 5 points
    public static void main(String[] args) {
        Circle[] circles = {
            new Circle(),
            new Circle(2, 3, 5),
            new Circle(-1, -1, 2.5)
        };
        double[][] points = { {0, 0}, {1, 0}, {5, 7}, {-3, -2}, {10, 10} };

        for (Circle c : circles) {
            c.displayInfo();
            System.out.printf("  Area: %.2f%n", c.area());
            System.out.printf("  Perimeter: %.2f%n", c.perimeter());
            for (double[] p : points) {
                System.out.println("  Contains (" + p[0] + ", " + p[1] + ")? " + c.contains(p[0], p[1]));
            }
        }
    }
}
