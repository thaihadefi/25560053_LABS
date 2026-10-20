public abstract class Student {
    private String studentId;
    private String fullName;
    private String address;
    private int totalCredits;
    private double gpa;

    // Input: studentId, fullName, address, totalCredits, gpa
    // Output: new student
    // Approach: credits and GPA via setters
    public Student(String studentId, String fullName, String address, int totalCredits, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.address = address;
        setTotalCredits(totalCredits);
        setGpa(gpa);
    }

    // Input: none
    // Output: total credits
    // Approach: return totalCredits
    public int getTotalCredits() {
        return totalCredits;
    }

    // Input: totalCredits
    // Output: none, credits updated if valid
    // Approach: >= 0 -> update, else print error
    public void setTotalCredits(int totalCredits) {
        if (totalCredits >= 0) {
            this.totalCredits = totalCredits;
        } else {
            System.out.println("Total credits must not be negative!");
        }
    }

    // Input: none
    // Output: GPA
    // Approach: return gpa
    public double getGpa() {
        return gpa;
    }

    // Input: gpa
    // Output: none, GPA updated if valid
    // Approach: in [0, 10] -> update, else print error
    public void setGpa(double gpa) {
        if (gpa >= 0 && gpa <= 10) {
            this.gpa = gpa;
        } else {
            System.out.println("GPA must be from 0 to 10!");
        }
    }

    // Input: none
    // Output: true if the student can graduate
    // Approach: implemented by subclasses
    public abstract boolean isEligibleForGraduation();

    // Input: none
    // Output: text with common info, one field per line
    // Approach: join fields; subclasses add their own
    @Override
    public String toString() {
        return "ID: " + studentId + "\n"
                + "Name: " + fullName + "\n"
                + "Address: " + address + "\n"
                + "Credits: " + totalCredits + "\n"
                + "GPA: " + gpa;
    }
}
