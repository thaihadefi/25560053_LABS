public abstract class Employee {
    private String type;
    private String employeeId;
    private String fullName;
    private String department;

    // Input: type, employeeId, fullName, department
    // Output: new employee
    // Approach: empty ID -> exception, store fields
    public Employee(String type, String employeeId, String fullName, String department) {
        if (employeeId.isBlank()) {
            throw new IllegalArgumentException("Employee ID must not be empty");
        }
        this.type = type;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;
    }

    // Input: none
    // Output: type name
    // Approach: return type
    public String getType() {
        return type;
    }

    // Input: none
    // Output: employee ID
    // Approach: return employeeId
    public String getEmployeeId() {
        return employeeId;
    }

    // Input: none
    // Output: full name
    // Approach: return fullName
    public String getFullName() {
        return fullName;
    }

    // Input: none
    // Output: department
    // Approach: return department
    public String getDepartment() {
        return department;
    }

    // Input: none
    // Output: monthly salary in VND
    // Approach: implemented by subclasses
    public abstract double calculateSalary();

    // Input: none
    // Output: prints common info
    // Approach: print type, then each field on one line; subclasses add their own
    public void displayInfo() {
        System.out.println("Type: " + type);
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + fullName);
        System.out.println("Department: " + department);
    }
}
