import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Input: sc, prompt
    // Output: non-empty line read from keyboard
    // Approach: repeat until the line is not blank
    private static String readText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("This field cannot be empty!");
        }
    }

    // Input: sc, prompt, min, max
    // Output: integer in [min, max] read from keyboard
    // Approach: repeat until the line is an integer in range
    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    // Input: sc, prompt
    // Output: number >= 0 read from keyboard
    // Approach: repeat until the line is a non-negative number
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value >= 0 && value < Double.POSITIVE_INFINITY) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input, please try again.");
        }
    }

    // Input: employees, id
    // Output: employee with this ID, null if not found
    // Approach: loop, compare IDs ignoring case
    private static Employee findEmployee(ArrayList<Employee> employees, String id) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }

    // Input: employees, name, department
    // Output: first employee with this name and department, null if not found
    // Approach: overload of findEmployee(), compare both fields ignoring case
    private static Employee findEmployee(ArrayList<Employee> employees, String name, String department) {
        for (Employee e : employees) {
            if (e.getFullName().equalsIgnoreCase(name) && e.getDepartment().equalsIgnoreCase(department)) {
                return e;
            }
        }
        return null;
    }

    // Input: sc, employees
    // Output: none, N new employees added
    // Approach: read each employee, repeat the ID until it is not a duplicate
    private static void addEmployees(Scanner sc, ArrayList<Employee> employees) {
        int n = readInt(sc, "Enter number of employees: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nEmployee " + (i + 1));
            int type = readInt(sc, "Type (1-Full-time, 2-Part-time): ", 1, 2);
            String id = readText(sc, "Employee ID: ");
            while (findEmployee(employees, id) != null) {
                System.out.println("Employee ID already exists!");
                id = readText(sc, "Employee ID: ");
            }
            String name = readText(sc, "Full name: ");
            String department = readText(sc, "Department: ");

            if (type == 1) {
                double base = readDouble(sc, "Base salary: ");
                double allowance = readDouble(sc, "Allowance: ");
                int workdays = readInt(sc, "Workdays: ", 0, 31);
                employees.add(new FullTimeEmployee(id, name, department, base, allowance, workdays));
            } else {
                double hours = readDouble(sc, "Hours worked: ");
                double rate = readDouble(sc, "Hourly rate: ");
                employees.add(new PartTimeEmployee(id, name, department, hours, rate));
            }
        }
    }

    // Input: employee
    // Output: prints the employee and the salary
    // Approach: displayInfo() and calculateSalary() on an Employee reference
    private static void display(Employee employee) {
        employee.displayInfo();
        System.out.printf("Salary: %,.2f VND%n", employee.calculateSalary());
    }

    // Input: employee (may be null)
    // Output: prints the employee or a not-found message
    // Approach: null -> message, else display()
    private static void displayResult(Employee employee) {
        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            display(employee);
        }
    }

    // Input: employees, department
    // Output: prints employees of the department or a not-found message
    // Approach: loop, compare departments ignoring case
    private static void displayDepartment(ArrayList<Employee> employees, String department) {
        boolean found = false;
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                System.out.println();
                display(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No employee in this department.");
        }
    }

    // Input: employees
    // Output: total payroll in VND
    // Approach: loop, add calculateSalary()
    private static double totalPayroll(ArrayList<Employee> employees) {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateSalary();
        }
        return total;
    }

    // Input: employees (not empty)
    // Output: employee with the highest salary
    // Approach: loop, keep the max calculateSalary()
    private static Employee findHighestSalary(ArrayList<Employee> employees) {
        Employee best = employees.get(0);
        for (Employee e : employees) {
            if (e.calculateSalary() > best.calculateSalary()) {
                best = e;
            }
        }
        return best;
    }

    // Input: menu choices and employee info from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add employees  2. Display payroll  3. Find by ID  "
                    + "4. Find by name and department  5. Find by department  6. Statistics  0. Exit");
            int choice = readInt(sc, "Choice: ", 0, 6);

            if (choice == 0) {
                break;
            }
            if (choice != 1 && employees.isEmpty()) {
                System.out.println("The list is empty.");
                continue;
            }

            switch (choice) {
                case 1:
                    addEmployees(sc, employees);
                    break;
                case 2:
                    for (Employee e : employees) {
                        System.out.println();
                        display(e);
                    }
                    break;
                case 3:
                    displayResult(findEmployee(employees, readText(sc, "Employee ID: ")));
                    break;
                case 4:
                    displayResult(findEmployee(employees, readText(sc, "Full name: "), readText(sc, "Department: ")));
                    break;
                case 5:
                    displayDepartment(employees, readText(sc, "Department: "));
                    break;
                case 6:
                    System.out.printf("Total payroll: %,.2f VND%n", totalPayroll(employees));
                    System.out.println("\nEmployee with the highest salary:");
                    display(findHighestSalary(employees));
                    break;
            }
        }

        sc.close();
    }
}
