import java.util.Scanner;

public class Main {
    // Input: s
    // Output: s reversed
    // Approach: loop from last char to first, append each char
    private static String reverse(String s) {
        String reversed = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed += s.charAt(i);
        }
        return reversed;
    }

    // Input: a line from keyboard
    // Output: prints it reversed
    // Approach: call reverse(s)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        System.out.println("Reversed string: " + reverse(s));

        sc.close();
    }
}
