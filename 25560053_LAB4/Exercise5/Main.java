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
    // Output: non-negative number read from keyboard
    // Approach: repeat until the line is a finite number >= 0
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

    // Input: courses, type
    // Output: courses of this type
    // Approach: loop, keep courses whose getType() matches
    private static ArrayList<CourseRegistration> findByType(ArrayList<CourseRegistration> courses, String type) {
        ArrayList<CourseRegistration> result = new ArrayList<>();
        for (CourseRegistration c : courses) {
            if (c.getType().equals(type)) {
                result.add(c);
            }
        }
        return result;
    }

    // Input: courses
    // Output: course with the largest expected collection, null if empty
    // Approach: keep the best so far, replace when a collection is higher
    private static CourseRegistration findMax(ArrayList<CourseRegistration> courses) {
        if (courses.isEmpty()) {
            return null;
        }
        CourseRegistration best = courses.get(0);
        for (CourseRegistration c : courses) {
            if (c.calculateTotalCollection() > best.calculateTotalCollection()) {
                best = c;
            }
        }
        return best;
    }

    // Input: courses
    // Output: total expected collection in VND
    // Approach: sum calculateTotalCollection() on each course
    private static double total(ArrayList<CourseRegistration> courses) {
        double sum = 0;
        for (CourseRegistration c : courses) {
            sum += c.calculateTotalCollection();
        }
        return sum;
    }

    // Input: courses
    // Output: total registered students
    // Approach: sum getRegisteredStudents()
    private static int totalStudents(ArrayList<CourseRegistration> courses) {
        int sum = 0;
        for (CourseRegistration c : courses) {
            sum += c.getRegisteredStudents();
        }
        return sum;
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
                System.out.println("Code already exists!");
                code = readText(sc, "Course code: ");
            }
            String name = readText(sc, "Course name: ");
            int credits = readInt(sc, "Credits: ", 1, Integer.MAX_VALUE);
            int students = readInt(sc, "Registered students: ", 0, Integer.MAX_VALUE);

            if (type == 1) {
                courses.add(new TheoryCourse(code, name, credits, students));
            } else {
                double equipmentFee = readDouble(sc, "Equipment fee (VND/student): ");
                courses.add(new LabCourse(code, name, credits, students, equipmentFee));
            }
        }
    }

    // Input: course
    // Output: prints the course, tuition per student and total, or Not found.
    // Approach: displayInfo(), tuitionPerStudent(), calculateTotalCollection() on a CourseRegistration reference
    private static void display(CourseRegistration course) {
        if (course == null) {
            System.out.println("Not found.");
            return;
        }
        course.displayInfo();
        System.out.printf("Tuition per student: %,.2f VND%n", course.tuitionPerStudent());
        System.out.printf("Expected total collection: %,.2f VND%n", course.calculateTotalCollection());
    }

    // Input: courses
    // Output: prints every course, or Not found. if empty
    // Approach: overload of display(), loop and call display(course)
    private static void display(ArrayList<CourseRegistration> courses) {
        if (courses.isEmpty()) {
            System.out.println("Not found.");
        }
        for (CourseRegistration c : courses) {
            System.out.println();
            display(c);
        }
    }

    // Input: courses (not empty)
    // Output: prints counts, total, weighted average (N/A if 0 students), largest collection
    // Approach: findByType(...).size(), total(), total / totalStudents(), findMax()
    private static void printStatistics(ArrayList<CourseRegistration> courses) {
        System.out.println("Theory courses: " + findByType(courses, "Theory").size());
        System.out.println("Laboratory courses: " + findByType(courses, "Laboratory").size());
        System.out.printf("Total expected collection: %,.2f VND%n", total(courses));
        int students = totalStudents(courses);
        if (students == 0) {
            System.out.println("Average tuition per student: N/A");
        } else {
            System.out.printf("Average tuition per student: %,.2f VND%n", total(courses) / students);
        }
        System.out.println("\nLargest expected collection:");
        display(findMax(courses));
    }

    // Input: none
    // Output: prints the menu
    // Approach: one line per option, 0 exits
    private static void printMenu() {
        System.out.println("\n=== Course Registration ===");
        System.out.println("1. Add courses");
        System.out.println("2. Display all");
        System.out.println("3. Find by course code");
        System.out.println("4. Statistics");
        System.out.println("0. Exit");
    }

    // Input: menu choices and data from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<CourseRegistration> courses = new ArrayList<>();

        while (true) {
            printMenu();
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
                    display(findCourse(courses, readText(sc, "Course code: ")));
                    break;
                case 4:
                    printStatistics(courses);
                    break;
            }
        }

        sc.close();
    }
}
