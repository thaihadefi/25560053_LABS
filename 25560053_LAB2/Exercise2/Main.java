public class Main {
    // Input: none
    // Output: results of all operations
    // Purpose: test Fraction
    // Approach: use all constructors and operations, check copy is a different object
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(3, -4);

        System.out.print("f1 = ");
        f1.display();
        System.out.print("f2 = ");
        f2.display();

        System.out.print("f1 + f2 = ");
        f1.add(f2).display();
        System.out.print("f1 - f2 = ");
        f1.subtract(f2).display();
        System.out.print("f1 * f2 = ");
        f1.multiply(f2).display();
        System.out.print("f1 / f2 = ");
        f1.divide(f2).display();

        System.out.print("Default fraction = ");
        new Fraction().display();
        System.out.print("Fraction(5, 0) = ");
        new Fraction(5, 0).display();

        try {
            f1.divide(new Fraction());
        } catch (ArithmeticException e) {
            System.out.println("f1 / 0: " + e.getMessage());
        }

        Fraction original = new Fraction(6, 8);
        Fraction copy = new Fraction(original);
        Fraction sameRef = original;
        System.out.println("copy == original? " + (copy == original));
        System.out.println("sameRef == original? " + (sameRef == original));
        copy.simplify();
        System.out.print("after simplifying the copy, copy = ");
        copy.display();
        System.out.print("original is unchanged = ");
        original.display();
    }
}
