import java.util.Scanner;

public class Main {
    private static final double EPS = 1e-9;

    // Input: hit times, x, y, z
    // Output: total damage
    // Approach: sliding window, drop quills older than z (age == z still counts),
    // each hit deals x + (number of quills left) * y
    private static double getTotalDamage(double[] times, double x, double y, double z) {
        double totalDamage = 0;
        int l = 0;

        for (int r = 0; r < times.length; r++) {
            while (times[r] - times[l] > z + EPS) {
                l++;
            }
            totalDamage += x + (r - l) * y;
        }

        return totalDamage;
    }

    // Input: n, n hit times, x, y, z from keyboard
    // Output: prints total damage
    // Approach: read input, call getTotalDamage
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of hits: ");
        int n = sc.nextInt();

        double[] times = new double[n];
        System.out.print("Enter hit times: ");
        for (int i = 0; i < n; i++) {
            times[i] = sc.nextDouble();
        }

        System.out.print("Enter base damage (x): ");
        double x = sc.nextDouble();

        System.out.print("Enter stack damage (y): ");
        double y = sc.nextDouble();

        System.out.print("Enter quill duration (z): ");
        double z = sc.nextDouble();

        double totalDamage = getTotalDamage(times, x, y, z);

        System.out.println(totalDamage == (long) totalDamage ? String.valueOf((long) totalDamage) : String.valueOf(totalDamage));

        sc.close();
    }
}
