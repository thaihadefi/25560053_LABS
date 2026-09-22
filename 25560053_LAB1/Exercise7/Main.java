import java.util.Scanner;

public class Main {
    private static int[] mergeSortedArrays(int[] a, int n, int[] b, int m) {
        int[] merged = new int[n + m];
        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            merged[k++] = (a[i] <= b[j]) ? a[i++] : b[j++];
        }
        while (i < n) {
            merged[k++] = a[i++];
        }
        while (j < m) {
            merged[k++] = b[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements in first array: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.print("Enter elements of first array: ");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.print("Enter number of elements in second array: ");
        int m = sc.nextInt();
        int[] b = new int[m];
        System.out.print("Enter elements of second array: ");
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        int[] merged = mergeSortedArrays(a, n, b, m);

        System.out.print("Merged sorted array: ");
        for (int idx = 0; idx < merged.length; idx++) {
            System.out.print(merged[idx] + (idx < merged.length - 1 ? " " : ""));
        }
        System.out.println();

        sc.close();
    }
}
