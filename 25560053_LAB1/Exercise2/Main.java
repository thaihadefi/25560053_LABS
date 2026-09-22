import java.util.Scanner;

public class Main {
    private static int findLargest(int a, int b, int c) {
        return (a >= b && a >= c) ? a : (b >= a && b >= c) ? b : c;
    }

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
