import java.util.Scanner;

public class Main {
    // Input: n from keyboard
    // Output: count and probability of each face
    // Purpose: test Dice
    // Approach: roll n times, cnt[face]++, cnt / n
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rolls: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Number of rolls must be positive.");
            sc.close();
            return;
        }

        Dice dice = new Dice();
        int[] cnt = new int[7];
        for (int i = 0; i < n; i++) {
            cnt[dice.roll()]++;
        }

        for (int face = 1; face <= 6; face++) {
            System.out.printf("Face %d: %d times, probability = %.4f%n", face, cnt[face], (double) cnt[face] / n);
        }

        sc.close();
    }
}
