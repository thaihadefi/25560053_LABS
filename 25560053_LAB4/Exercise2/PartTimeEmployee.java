public class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    // Input: employeeId, fullName, department, hoursWorked, hourlyRate
    // Output: new part-time employee
    // Approach: super(...), other fields via setters
    public PartTimeEmployee(String employeeId, String fullName, String department, double hoursWorked,
            double hourlyRate) {
        super("Part-Time", employeeId, fullName, department);
        setHoursWorked(hoursWorked);
        setHourlyRate(hourlyRate);
    }

    // Input: none
    // Output: hours worked
    // Approach: return hoursWorked
    public double getHoursWorked() {
        return hoursWorked;
    }

    // Input: hoursWorked
    // Output: none, hours worked updated
    // Approach: < 0 -> exception, else update
    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative");
        }
        this.hoursWorked = hoursWorked;
    }

    // Input: none
    // Output: hourly rate
    // Approach: return hourlyRate
    public double getHourlyRate() {
        return hourlyRate;
    }

    // Input: hourlyRate
    // Output: none, hourly rate updated
    // Approach: < 0 -> exception, else update
    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative");
        }
        this.hourlyRate = hourlyRate;
    }

    @Override
    // Input: none
    // Output: monthly salary in VND
    // Approach: hours worked x hourly rate
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    @Override
    // Input: none
    // Output: prints common info, hours, hourly rate
    // Approach: super.displayInfo(), then own fields
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.printf("Hourly Rate: %,.2f VND%n", hourlyRate);
    }
}
