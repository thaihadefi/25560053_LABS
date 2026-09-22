import java.util.Scanner;

public class Main {
    // Input: arr, x
    // Output: first index of x, or -1
    // Approach: linear search from the start
    private static int findFirstIdx(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // Input: n, n numbers, then x from keyboard
    // Output: prints first index of x, or -1
    // Approach: read into an array, call findFirstIdx(arr, x)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter element to find: ");
        int x = sc.nextInt();

        System.out.println(findFirstIdx(arr, x));

        sc.close();
    }
}
