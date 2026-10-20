import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Input: sc, prompt
    // Output: non-empty line read from keyboard
    // Approach: repeat until the line is not blank
    private static String readText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("This field cannot be empty!");
        }
    }

    // Input: sc, prompt, min, max
    // Output: integer in [min, max] read from keyboard
    // Approach: repeat until the line is an integer in range
    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    // Input: list of singers
    // Output: singer with the highest salary, null if the list is empty
    // Approach: loop, keep the max calculateSalary()
    private static Singer findHighestPaid(ArrayList<Singer> singers) {
        Singer best = null;
        for (Singer s : singers) {
            if (best == null || s.calculateSalary() > best.calculateSalary()) {
                best = s;
            }
        }
        return best;
    }

    // Input: n, then type and info of each singer from keyboard
    // Output: salary of each singer, highest-paid singer
    // Approach: read into ArrayList<Singer>, print salaries, findHighestPaid()
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Singer> singers = new ArrayList<>();

        int n = readInt(sc, "Enter number of singers: ", 1, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            System.out.println("\nSinger " + (i + 1));
            int type = readInt(sc, "Type (1-Regular, 2-Famous): ", 1, 2);
            String name = readText(sc, "Full name: ");
            int years = readInt(sc, "Years of experience: ", 0, Integer.MAX_VALUE);
            int albums = readInt(sc, "Albums sold: ", 0, Integer.MAX_VALUE);
            int performances = readInt(sc, "Performances: ", 0, Integer.MAX_VALUE);

            if (type == 1) {
                singers.add(new RegularSinger(name, years, albums, performances));
            } else {
                int gameShows = readInt(sc, "Game shows attended: ", 0, Integer.MAX_VALUE);
                singers.add(new FamousSinger(name, years, albums, performances, gameShows));
            }
        }

        System.out.println("\nSalaries:");
        for (Singer s : singers) {
            System.out.println(s.getFullName() + ": " + s.calculateSalary() + " VND");
        }

        Singer best = findHighestPaid(singers);
        System.out.println("\nHighest-paid singer:");
        System.out.println(best);

        sc.close();
    }
}
