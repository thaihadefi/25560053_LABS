public class UITStudent {
    // Attributes
    private String studentId;
    private String fullName;
    private String major;
    private double gpa;

    // No-argument constructor
    // Input: none
    // Output: student with default info
    // Purpose: no info yet
    // Approach: this("N/A", "Unknown")
    public UITStudent() {
        this("N/A", "Unknown");
    }

    // Parameterized constructor
    // Input: studentId, fullName
    // Output: student with no major, GPA 0
    // Purpose: only ID and name known
    // Approach: this(studentId, fullName, "Undeclared", 0.0)
    public UITStudent(String studentId, String fullName) {
        this(studentId, fullName, "Undeclared", 0.0);
    }

    // Parameterized constructor
    // Input: studentId, fullName, major, gpa
    // Output: new student
    // Purpose: full info
    // Approach: GPA outside [0, 100] -> 0
    public UITStudent(String studentId, String fullName, String major, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.major = major;
        this.gpa = (gpa >= 0 && gpa <= 100) ? gpa : 0;
    }

    // Methods
    // Input: none
    // Output: true if passed
    // Purpose: check pass
    // Approach: gpa >= 40
    public boolean isPassed() {
        return gpa >= 40;
    }

    // Input: none
    // Output: First Class (1st) / Upper Second Class (Division 1) / Lower Second Class (Division 2) / Third Class (3rd) / Fail
    // Purpose: classify by GPA
    // Approach: if-else: >= 70, >= 60, >= 50, >= 40, else Fail
    public String academicClassification() {
        if (gpa >= 70.0) {
            return "First Class (1st)";
        } else if (gpa >= 60.0) {
            return "Upper Second Class (Division 1)";
        } else if (gpa >= 50.0) {
            return "Lower Second Class (Division 2)";
        } else if (gpa >= 40.0) {
            return "Third Class (3rd)";
        } else {
            return "Fail";
        }
    }

    // Input: none
    // Output: prints ID, name, major, GPA
    // Purpose: show student
    // Approach: printf all fields
    public void displayInfo() {
        System.out.printf("%s - %s - %s - GPA: %.2f%n", studentId, fullName, major, gpa);
    }
}
