public class FullTimeEmployee extends Employee {
    private static final int BASE_WORKDAYS = 26;
    private static final int MAX_WORKDAYS = 31;
    private double baseSalary;
    private double allowance;
    private int workdays;

    // Input: employeeId, fullName, department, baseSalary, allowance, workdays
    // Output: new full-time employee
    // Approach: super(...), other fields via setters
    public FullTimeEmployee(String employeeId, String fullName, String department, double baseSalary,
            double allowance, int workdays) {
        super("Full-Time", employeeId, fullName, department);
        setBaseSalary(baseSalary);
        setAllowance(allowance);
        setWorkdays(workdays);
    }

    // Input: none
    // Output: base salary
    // Approach: return baseSalary
    public double getBaseSalary() {
        return baseSalary;
    }

    // Input: none
    // Output: allowance
    // Approach: return allowance
    public double getAllowance() {
        return allowance;
    }

    // Input: none
    // Output: workdays
    // Approach: return workdays
    public int getWorkdays() {
        return workdays;
    }

    // Input: baseSalary
    // Output: none, base salary updated
    // Approach: < 0 -> exception, else update
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary cannot be negative");
        }
        this.baseSalary = baseSalary;
    }

    // Input: allowance
    // Output: none, allowance updated
    // Approach: < 0 -> exception, else update
    public void setAllowance(double allowance) {
        if (allowance < 0) {
            throw new IllegalArgumentException("Allowance cannot be negative");
        }
        this.allowance = allowance;
    }

    // Input: workdays
    // Output: none, workdays updated
    // Approach: outside 0..31 -> exception, else update
    public void setWorkdays(int workdays) {
        if (workdays < 0 || workdays > MAX_WORKDAYS) {
            throw new IllegalArgumentException("Workdays must be between 0 and " + MAX_WORKDAYS);
        }
        this.workdays = workdays;
    }

    @Override
    // Input: none
    // Output: monthly salary in VND
    // Approach: base salary x min(workdays, 26) / 26 + allowance
    public double calculateSalary() {
        return baseSalary * Math.min(workdays, BASE_WORKDAYS) / BASE_WORKDAYS + allowance;
    }

    @Override
    // Input: none
    // Output: prints common info, base salary, allowance, workdays
    // Approach: super.displayInfo(), then own fields
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Base Salary: %,.2f VND%n", baseSalary);
        System.out.printf("Allowance: %,.2f VND%n", allowance);
        System.out.println("Workdays: " + workdays);
    }
}
