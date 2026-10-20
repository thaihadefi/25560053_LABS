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

    // Input: sc, prompt, min, max
    // Output: decimal number in [min, max] read from keyboard
    // Approach: repeat until the line is a number in range
    private static double readDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    // Input: list of students
    // Output: number of students who can graduate
    // Approach: loop, call isEligibleForGraduation()
    private static int countEligible(ArrayList<Student> students) {
        int count = 0;
        for (Student s : students) {
            if (s.isEligibleForGraduation()) {
                count++;
            }
        }
        return count;
    }

    // Input: list of students
    // Output: university student with the highest GPA, null if there is none
    // Approach: loop, skip non-UniversityStudent, keep the max GPA
    private static UniversityStudent findTopUniversityStudent(ArrayList<Student> students) {
        UniversityStudent best = null;
        for (Student s : students) {
            if (s instanceof UniversityStudent) {
                UniversityStudent u = (UniversityStudent) s;
                if (best == null || u.getGpa() > best.getGpa()) {
                    best = u;
                }
            }
        }
        return best;
    }

    // Input: n, then type and info of each student from keyboard
    // Output: number of eligible students, top university student
    // Approach: read into ArrayList<Student>, then call helpers
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        int n = readInt(sc, "Enter number of students: ", 1, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));
            int type = readInt(sc, "Type (1-College, 2-University): ", 1, 2);
            String id = readText(sc, "Student ID: ");
            String name = readText(sc, "Full name: ");
            String address = readText(sc, "Address: ");
            int credits = readInt(sc, "Total credits: ", 0, Integer.MAX_VALUE);
            double gpa = readDouble(sc, "GPA: ", 0, 10);

            if (type == 1) {
                double exam = readDouble(sc, "Graduation exam score: ", 0, 10);
                students.add(new CollegeStudent(id, name, address, credits, gpa, exam));
            } else {
                String title = readText(sc, "Thesis title: ");
                double score = readDouble(sc, "Thesis score: ", 0, 10);
                students.add(new UniversityStudent(id, name, address, credits, gpa, title, score));
            }
        }

        System.out.println("\nEligible students: " + countEligible(students));

        UniversityStudent best = findTopUniversityStudent(students);
        if (best != null) {
            System.out.println("\nUniversity student with highest GPA:");
            System.out.println(best);
        } else {
            System.out.println("No university student found.");
        }

        sc.close();
    }
}
