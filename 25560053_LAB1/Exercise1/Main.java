import java.util.Scanner;

public class Main {
    // Input: n
    // Output: true if n is even
    // Approach: check n % 2 == 0
    private static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // Input: n from keyboard
    // Output: prints 1..n, each with Even or Odd
    // Approach: loop i = 1..n, use isEven(i)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println(i + " - " + (isEven(i) ? "Even" : "Odd"));
        }

        sc.close();
    }
}
