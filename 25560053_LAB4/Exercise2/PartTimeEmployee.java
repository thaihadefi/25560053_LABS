public class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    // Input: employeeId, fullName, department, hoursWorked, hourlyRate
    // Output: new part-time employee
    // Approach: super(...), numbers via setters
    public PartTimeEmployee(String employeeId, String fullName, String department, double hoursWorked,
            double hourlyRate) {
        super("Part-time", employeeId, fullName, department);
        setHoursWorked(hoursWorked);
        setHourlyRate(hourlyRate);
    }

    // Input: hoursWorked
    // Output: none, hours updated
    // Approach: < 0 -> exception, else update
    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked must not be negative");
        }
        this.hoursWorked = hoursWorked;
    }

    // Input: hourlyRate
    // Output: none, hourly rate updated
    // Approach: < 0 -> exception, else update
    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate must not be negative");
        }
        this.hourlyRate = hourlyRate;
    }

    // Input: none
    // Output: monthly salary in VND
    // Approach: hours worked x hourly rate
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    // Input: none
    // Output: prints common info and salary data
    // Approach: super.displayInfo(), then own fields
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hours worked: " + hoursWorked);
        System.out.printf("Hourly rate: %,.2f VND%n", hourlyRate);
    }
}
