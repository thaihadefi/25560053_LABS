public class CollegeStudent extends Student {
    private double examScore;

    // Input: common info, examScore
    // Output: new college student
    // Approach: super(...) for common info, setter for exam score
    public CollegeStudent(String studentId, String fullName, String address, int totalCredits, double gpa,
            double examScore) {
        super(studentId, fullName, address, totalCredits, gpa);
        setExamScore(examScore);
    }

    // Input: examScore
    // Output: none, score updated if valid
    // Approach: in [0, 10] -> update, else print error
    public void setExamScore(double examScore) {
        if (examScore >= 0 && examScore <= 10) {
            this.examScore = examScore;
        } else {
            System.out.println("Exam score must be from 0 to 10!");
        }
    }

    // Input: none
    // Output: true if eligible
    // Approach: credits >= 120 and GPA >= 5 and exam score >= 5
    @Override
    public boolean isEligibleForGraduation() {
        return getTotalCredits() >= 120 && getGpa() >= 5.0 && examScore >= 5.0;
    }

    // Input: none
    // Output: text with common info and exam score
    // Approach: super.toString(), then exam score
    @Override
    public String toString() {
        return super.toString() + "\n" + "Exam score: " + examScore;
    }
}
