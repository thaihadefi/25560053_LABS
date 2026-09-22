import java.util.Scanner;

public class Main {
    private static double getTotalDamage(double[] times, double x, double y, double z) {
        double totalDamage = 0;
        int l = 0;

        for (int r = 0; r < times.length; r++) {
            while (times[r] - times[l] > z) {
                l++;
            }
            totalDamage += x + (r - l) * y;
        }

        return totalDamage;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of hits: ");
        int n = sc.nextInt();
        if (n >= 10000) {
            System.out.println("Invalid input: n must be less than 10000.");
            sc.close();
            return;
        }

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
