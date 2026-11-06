public class TheoryCourse extends CourseRegistration {
    private static final double FEE_PER_CREDIT = 450000;

    // Input: courseCode, courseName, credits, registeredStudents
    // Output: new theory course
    // Approach: super(...)
    public TheoryCourse(String courseCode, String courseName, int credits, int registeredStudents) {
        super("Theory", courseCode, courseName, credits, registeredStudents);
    }

    // Input: none
    // Output: tuition per student in VND
    // Approach: credits x 450,000
    @Override
    public double tuitionPerStudent() {
        return getCredits() * FEE_PER_CREDIT;
    }
}
