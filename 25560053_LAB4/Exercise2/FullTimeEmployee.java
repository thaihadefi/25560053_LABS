public class FullTimeEmployee extends Employee {
    private static final int STANDARD_WORKDAYS = 26;
    private static final int MAX_WORKDAYS = 31;
    private double baseSalary;
    private double allowance;
    private int workdays;

    // Input: employeeId, fullName, department, baseSalary, allowance, workdays
    // Output: new full-time employee
    // Approach: super(...), numbers via setters
    public FullTimeEmployee(String employeeId, String fullName, String department, double baseSalary,
            double allowance, int workdays) {
        super("Full-time", employeeId, fullName, department);
        setBaseSalary(baseSalary);
        setAllowance(allowance);
        setWorkdays(workdays);
    }

    // Input: baseSalary
    // Output: none, base salary updated
    // Approach: < 0 -> exception, else update
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary must not be negative");
        }
        this.baseSalary = baseSalary;
    }

    // Input: allowance
    // Output: none, allowance updated
    // Approach: < 0 -> exception, else update
    public void setAllowance(double allowance) {
        if (allowance < 0) {
            throw new IllegalArgumentException("Allowance must not be negative");
        }
        this.allowance = allowance;
    }

    // Input: workdays
    // Output: none, workdays updated
    // Approach: outside [0, 31] -> exception, else update
    public void setWorkdays(int workdays) {
        if (workdays < 0 || workdays > MAX_WORKDAYS) {
            throw new IllegalArgumentException("Workdays must be from 0 to " + MAX_WORKDAYS);
        }
        this.workdays = workdays;
    }

    // Input: none
    // Output: monthly salary in VND
    // Approach: base salary x min(workdays, 26) / 26 + allowance
    @Override
    public double calculateSalary() {
        return baseSalary * Math.min(workdays, STANDARD_WORKDAYS) / STANDARD_WORKDAYS + allowance;
    }

    // Input: none
    // Output: prints common info and salary data
    // Approach: super.displayInfo(), then own fields
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Base salary: %,.2f VND%n", baseSalary);
        System.out.printf("Allowance: %,.2f VND%n", allowance);
        System.out.println("Workdays: " + workdays);
    }
}
