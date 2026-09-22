import java.util.Scanner;

public class Main {
    private static boolean isEven(int n) {
        return n % 2 == 0;
    }

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
