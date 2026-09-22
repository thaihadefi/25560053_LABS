import java.util.Scanner;

public class Main {
    // Input: n
    // Output: sum of digits of n
    // Approach: take abs, then add n % 10 and divide n by 10 until n = 0
    private static int getSumOfDigits(int n) {
        n = Math.abs(n);
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // Input: n from keyboard
    // Output: prints sum of digits
    // Approach: call getSumOfDigits(n)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int n = sc.nextInt();

        System.out.println("Sum of digits: " + getSumOfDigits(n));

        sc.close();
    }
}
