public class Main {
    // Input: none
    // Output: info, area, perimeter of each rectangle
    // Purpose: test Rectangle
    // Approach: create 4 rectangles (1 invalid), call all methods
    public static void main(String[] args) {
        Rectangle[] rectangles = {
            new Rectangle(),
            new Rectangle(4, 5),
            new Rectangle(2.5, 3),
            new Rectangle(-2, 3)
        };

        for (Rectangle r : rectangles) {
            r.displayInfo();
            System.out.println("  Area: " + r.area());
            System.out.println("  Perimeter: " + r.perimeter());
        }
    }
}
