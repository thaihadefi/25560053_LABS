public class Fraction {
    // Attributes
    private int numerator;
    private int denominator;

    // No-argument constructor
    // Input: none
    // Output: 0/1
    // Purpose: default fraction
    // Approach: this(0, 1)
    public Fraction() {
        this(0, 1);
    }

    // Parameterized constructor
    // Input: numerator, denominator
    // Output: new fraction
    // Purpose: fraction with given value
    // Approach: denominator 0 -> 1, keep denominator positive
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            denominator = 1;
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Copy constructor
    // Input: other
    // Output: copy of other
    // Purpose: copy a fraction
    // Approach: copy both fields
    public Fraction(Fraction other) {
        this.numerator = other.numerator;
        this.denominator = other.denominator;
    }

    // Methods
    // Input: a, b
    // Output: gcd of a and b
    // Purpose: helper for simplify()
    // Approach: Euclid: (a, b) -> (b, a % b) until b = 0
    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    // Input: none
    // Output: none, fraction is reduced
    // Purpose: reduce to lowest terms
    // Approach: divide both by gcd
    public void simplify() {
        int g = gcd(numerator, denominator);
        numerator /= g;
        denominator /= g;
    }

    // Input: other
    // Output: this + other
    // Purpose: add fractions
    // Approach: a/b + c/d = (ad + cb) / bd, simplify
    public Fraction add(Fraction other) {
        Fraction ans = new Fraction(numerator * other.denominator + other.numerator * denominator,
                denominator * other.denominator);
        ans.simplify();
        return ans;
    }

    // Input: other
    // Output: this - other
    // Purpose: subtract fractions
    // Approach: a/b - c/d = (ad - cb) / bd, simplify
    public Fraction subtract(Fraction other) {
        Fraction ans = new Fraction(numerator * other.denominator - other.numerator * denominator,
                denominator * other.denominator);
        ans.simplify();
        return ans;
    }

    // Input: other
    // Output: this * other
    // Purpose: multiply fractions
    // Approach: a/b * c/d = ac / bd, simplify
    public Fraction multiply(Fraction other) {
        Fraction ans = new Fraction(numerator * other.numerator, denominator * other.denominator);
        ans.simplify();
        return ans;
    }

    // Input: other
    // Output: this / other
    // Purpose: divide fractions
    // Approach: a/b / c/d = ad / bc, simplify; other = 0 -> exception
    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        Fraction ans = new Fraction(numerator * other.denominator, denominator * other.numerator);
        ans.simplify();
        return ans;
    }

    // Input: none
    // Output: prints numerator/denominator
    // Purpose: show fraction
    // Approach: print both fields
    public void display() {
        System.out.println(numerator + "/" + denominator);
    }
}
