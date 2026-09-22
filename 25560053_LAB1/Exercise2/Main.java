import java.util.Scanner;

public class Main {
    // Input: a, b, c
    // Output: largest of the three
    // Approach: compare each number with the other two
    private static int findLargest(int a, int b, int c) {
        return (a >= b && a >= c) ? a : (b >= a && b >= c) ? b : c;
    }

    // Input: 3 numbers from keyboard
    // Output: prints the largest
    // Approach: call findLargest(a, b, c)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter three numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        System.out.println("The largest number is: " + findLargest(a, b, c));

        sc.close();
    }
}
