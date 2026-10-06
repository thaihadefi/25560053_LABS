public class Main {
    // Input: none
    // Output: each call, result and chosen method
    // Purpose: test overloading
    // Approach: call every version, Java picks by argument types
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // int, int -> add(int, int)
        System.out.println("add(2, 3) = " + calc.add(2, 3) + "  -> add(int, int)");
        // double, double -> add(double, double)
        System.out.println("add(2.5, 3.1) = " + calc.add(2.5, 3.1) + "  -> add(double, double)");
        // 3 ints -> add(int, int, int)
        System.out.println("add(1, 2, 3) = " + calc.add(1, 2, 3) + "  -> add(int, int, int)");
        // int, double -> no add(int, double), 2 becomes 2.0 -> add(double, double)
        System.out.println("add(2, 3.5) = " + calc.add(2, 3.5) + "  -> add(double, double), 2 widened to double");
        // int, int -> max(int, int)
        System.out.println("max(4, 9) = " + calc.max(4, 9) + "  -> max(int, int)");
        // double, double -> max(double, double)
        System.out.println("max(2.5, 1.5) = " + calc.max(2.5, 1.5) + "  -> max(double, double)");
        // int, double -> 7 becomes 7.0 -> max(double, double)
        System.out.println("max(7, 2.5) = " + calc.max(7, 2.5) + "  -> max(double, double), 7 widened to double");
    }
}
