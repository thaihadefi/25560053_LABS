import java.util.Scanner;

public class Main {
    // Input: n
    // Output: prints n x 1 = ... up to n x 10 = ...
    // Approach: loop i = 1..10, print n * i
    private static void printMultiplicationTable(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }
    }

    // Input: n from keyboard
    // Output: prints the multiplication table of n
    // Approach: call printMultiplicationTable(n)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        printMultiplicationTable(n);

        sc.close();
    }
}
