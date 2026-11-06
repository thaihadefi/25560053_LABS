public abstract class CourseRegistration {
    private String type;
    private String courseCode;
    private String courseName;
    private int credits;
    private int registeredStudents;

    // Input: type, courseCode, courseName, credits, registeredStudents
    // Output: new course registration
    // Approach: empty code -> exception, numbers via setters
    public CourseRegistration(String type, String courseCode, String courseName, int credits, int registeredStudents) {
        if (courseCode.isBlank()) {
            throw new IllegalArgumentException("Course code cannot be empty");
        }
        this.type = type;
        this.courseCode = courseCode;
        this.courseName = courseName;
        setCredits(credits);
        setRegisteredStudents(registeredStudents);
    }

    // Input: none
    // Output: type
    // Approach: return type
    public String getType() {
        return type;
    }

    // Input: none
    // Output: course code
    // Approach: return courseCode
    public String getCourseCode() {
        return courseCode;
    }

    // Input: none
    // Output: course name
    // Approach: return courseName
    public String getCourseName() {
        return courseName;
    }

    // Input: none
    // Output: credits
    // Approach: return credits
    public int getCredits() {
        return credits;
    }

    // Input: credits
    // Output: none, credits updated
    // Approach: < 1 -> exception, else update
    public void setCredits(int credits) {
        if (credits < 1) {
            throw new IllegalArgumentException("Credits must be at least 1");
        }
        this.credits = credits;
    }

    // Input: none
    // Output: registered students
    // Approach: return registeredStudents
    public int getRegisteredStudents() {
        return registeredStudents;
    }

    // Input: registeredStudents
    // Output: none, registered students updated
    // Approach: < 0 -> exception, else update
    public void setRegisteredStudents(int registeredStudents) {
        if (registeredStudents < 0) {
            throw new IllegalArgumentException("Registered students cannot be negative");
        }
        this.registeredStudents = registeredStudents;
    }

    // Input: none
    // Output: tuition per student in VND
    // Approach: implemented by subclasses
    public abstract double tuitionPerStudent();

    // Input: none
    // Output: expected total collection in VND
    // Approach: tuitionPerStudent() x registered students
    public double calculateTotalCollection() {
        return tuitionPerStudent() * registeredStudents;
    }

    // Input: none
    // Output: prints code, type, name, credits, students
    // Approach: one field per line; LabCourse adds the equipment fee
    public void displayInfo() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Type: " + type);
        System.out.println("Course Name: " + courseName);
        System.out.println("Credits: " + credits);
        System.out.println("Registered Students: " + registeredStudents);
    }
}
