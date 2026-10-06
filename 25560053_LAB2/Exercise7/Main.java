public class Main {
    // Input: none
    // Output: info, pass status and classification of 5 students
    // Purpose: test UITStudent
    // Approach: create students with all 3 constructors, call every method on each
    public static void main(String[] args) {
        UITStudent[] students = {
            new UITStudent(),
            new UITStudent("25560004", "Nguyen Gia Bao"),
            new UITStudent("25560008", "Nguyen Hoang Hiep", "Computer Network and Security", 40.50),
            new UITStudent("25560060", "Nguyen Xuan Truong", "Computer Science", 67.27),
            new UITStudent("25560067", "Nguyen Ngoc Kim Uyen", "Computer Network and Security", 76.17)
        };

        for (UITStudent s : students) {
            s.displayInfo();
            System.out.println("  Passed: " + s.isPassed());
            System.out.println("  Classification: " + s.academicClassification());
        }
    }
}
