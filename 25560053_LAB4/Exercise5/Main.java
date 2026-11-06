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

    // Input: sc, prompt
    // Output: number >= 0 read from keyboard
    // Approach: repeat until the line is a non-negative number
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value >= 0 && value < Double.POSITIVE_INFINITY) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    // Input: courses, code
    // Output: course with this code, null if not found
    // Approach: loop, compare codes ignoring case
    private static CourseRegistration findCourse(ArrayList<CourseRegistration> courses, String code) {
        for (CourseRegistration c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    // Input: sc, courses
    // Output: none, N new courses added
    // Approach: read each course, repeat the code until it is not a duplicate
    private static void addCourses(Scanner sc, ArrayList<CourseRegistration> courses) {
        int n = readInt(sc, "Enter number of courses: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nCourse " + (i + 1));
            int type = readInt(sc, "Type (1-Theory, 2-Laboratory): ", 1, 2);
            String code = readText(sc, "Course code: ");
            while (findCourse(courses, code) != null) {
                System.out.println("Course code already exists!");
                code = readText(sc, "Course code: ");
            }
            String name = readText(sc, "Course name: ");
            int credits = readInt(sc, "Credits: ", 1, Integer.MAX_VALUE);
            int students = readInt(sc, "Registered students: ", 0, Integer.MAX_VALUE);

            if (type == 1) {
                courses.add(new TheoryCourse(code, name, credits, students));
            } else {
                double fee = readDouble(sc, "Equipment fee: ");
                courses.add(new LabCourse(code, name, credits, students, fee));
            }
        }
    }

    // Input: course
    // Output: prints the course, tuition and expected collection
    // Approach: displayInfo() and tuitionPerStudent() on a CourseRegistration reference
    private static void display(CourseRegistration course) {
        course.displayInfo();
        System.out.printf("Tuition per student: %,.2f VND%n", course.tuitionPerStudent());
        System.out.printf("Expected collection: %,.2f VND%n", course.expectedCollection());
    }

    // Input: courses
    // Output: prints every course
    // Approach: overload of display(), loop and call display(course)
    private static void display(ArrayList<CourseRegistration> courses) {
        for (CourseRegistration c : courses) {
            System.out.println();
            display(c);
        }
    }

    // Input: courses
    // Output: total expected collection in VND
    // Approach: loop, add expectedCollection()
    private static double totalCollection(ArrayList<CourseRegistration> courses) {
        double total = 0;
        for (CourseRegistration c : courses) {
            total += c.expectedCollection();
        }
        return total;
    }

    // Input: courses (not empty)
    // Output: course with the largest expected collection
    // Approach: loop, keep the max expectedCollection()
    private static CourseRegistration findLargestCollection(ArrayList<CourseRegistration> courses) {
        CourseRegistration best = courses.get(0);
        for (CourseRegistration c : courses) {
            if (c.expectedCollection() > best.expectedCollection()) {
                best = c;
            }
        }
        return best;
    }

    // Input: courses (not empty)
    // Output: prints type counts, total, weighted average, largest course
    // Approach: count with instanceof; average = total collection / total students, N/A if no student
    private static void printStatistics(ArrayList<CourseRegistration> courses) {
        int theory = 0;
        int lab = 0;
        long totalStudents = 0;
        for (CourseRegistration c : courses) {
            if (c instanceof TheoryCourse) {
                theory++;
            } else if (c instanceof LabCourse) {
                lab++;
            }
            totalStudents += c.getRegisteredStudents();
        }
        double total = totalCollection(courses);
        System.out.println("Theory courses: " + theory);
        System.out.println("Laboratory courses: " + lab);
        System.out.printf("Total expected collection: %,.2f VND%n", total);
        if (totalStudents == 0) {
            System.out.println("Average tuition per student: N/A");
        } else {
            System.out.printf("Average tuition per student: %,.2f VND/student%n", total / totalStudents);
        }
        System.out.println("\nCourse with the largest expected collection:");
        display(findLargestCollection(courses));
    }

    // Input: menu choices and course info from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<CourseRegistration> courses = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add courses  2. Display all  3. Search by code  4. Statistics  0. Exit");
            int choice = readInt(sc, "Choice: ", 0, 4);

            if (choice == 0) {
                break;
            }
            if (choice != 1 && courses.isEmpty()) {
                System.out.println("The list is empty.");
                continue;
            }

            switch (choice) {
                case 1:
                    addCourses(sc, courses);
                    break;
                case 2:
                    display(courses);
                    break;
                case 3:
                    CourseRegistration course = findCourse(courses, readText(sc, "Course code: "));
                    if (course == null) {
                        System.out.println("Course not found.");
                    } else {
                        display(course);
                    }
                    break;
                case 4:
                    printStatistics(courses);
                    break;
            }
        }

        sc.close();
    }
}
