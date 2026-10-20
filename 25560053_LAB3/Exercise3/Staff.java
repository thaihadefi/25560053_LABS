public class Staff extends Person {
    private String licenseId;
    private String employeeId;

    // Input: licenseId, employeeId, fullName, age
    // Output: new staff member
    // Approach: super(fullName, age)
    public Staff(String licenseId, String employeeId, String fullName, int age) {
        super(fullName, age);
        this.licenseId = licenseId;
        this.employeeId = employeeId;
    }

    // Input: none
    // Output: employee ID
    // Approach: return employeeId
    public String getEmployeeId() {
        return employeeId;
    }

    // Input: none
    // Output: text with IDs and person info
    // Approach: IDs, then super.toString()
    @Override
    public String toString() {
        return "Employee ID: " + employeeId + "\n"
                + "License ID: " + licenseId + "\n"
                + super.toString();
    }
}
