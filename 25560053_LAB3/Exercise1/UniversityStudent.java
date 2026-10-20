public class UniversityStudent extends Student {
    private String thesisTitle;
    private double thesisScore;

    // Input: common info, thesisTitle, thesisScore
    // Output: new university student
    // Approach: super(...) for common info, setter for thesis score
    public UniversityStudent(String studentId, String fullName, String address, int totalCredits, double gpa,
            String thesisTitle, double thesisScore) {
        super(studentId, fullName, address, totalCredits, gpa);
        this.thesisTitle = thesisTitle;
        setThesisScore(thesisScore);
    }

    // Input: thesisScore
    // Output: none, score updated if valid
    // Approach: in [0, 10] -> update, else print error
    public void setThesisScore(double thesisScore) {
        if (thesisScore >= 0 && thesisScore <= 10) {
            this.thesisScore = thesisScore;
        } else {
            System.out.println("Thesis score must be from 0 to 10!");
        }
    }

    // Input: none
    // Output: true if eligible
    // Approach: credits >= 170 and GPA >= 5 and thesis score >= 5
    @Override
    public boolean isEligibleForGraduation() {
        return getTotalCredits() >= 170 && getGpa() >= 5.0 && thesisScore >= 5.0;
    }

    // Input: none
    // Output: text with common info and thesis
    // Approach: super.toString(), then thesis title and score
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Thesis: " + thesisTitle + "\n"
                + "Thesis score: " + thesisScore;
    }
}
