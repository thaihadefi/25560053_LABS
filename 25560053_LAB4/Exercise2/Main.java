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
    // Output: non-negative number read from keyboard
    // Approach: repeat until the line is a finite number >= 0
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
    // Output: first employee with this name in this department, null if not found
    // Approach: overload of findEmployee(), compare both fields ignoring case
    private static Employee findEmployee(ArrayList<Employee> employees, String name, String department) {
        for (Employee e : employees) {
            if (e.getFullName().equalsIgnoreCase(name) && e.getDepartment().equalsIgnoreCase(department)) {
                return e;
            }
        }
        return null;
    }

    // Input: employees, department
    // Output: employees of this department
    // Approach: loop, keep matches ignoring case
    private static ArrayList<Employee> findByDepartment(ArrayList<Employee> employees, String department) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                result.add(e);
            }
        }
        return result;
    }

    // Input: employees
    // Output: employee with the highest salary, null if empty
    // Approach: keep the best so far, replace when a salary is higher
    private static Employee findMax(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            return null;
        }
        Employee best = employees.get(0);
        for (Employee e : employees) {
            if (e.calculateSalary() > best.calculateSalary()) {
                best = e;
            }
        }
        return best;
    }

    // Input: employees
    // Output: total payroll in VND
    // Approach: sum calculateSalary() on each Employee reference
    private static double total(ArrayList<Employee> employees) {
        double sum = 0;
        for (Employee e : employees) {
            sum += e.calculateSalary();
        }
        return sum;
    }

    // Input: sc, employees
    // Output: none, N new employees added
    // Approach: read each employee, repeat the ID until it is not a duplicate
    private static void addEmployees(Scanner sc, ArrayList<Employee> employees) {
        int n = readInt(sc, "Enter number of employees: ", 1, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            System.out.println("\nEmployee " + (i + 1));
            int type = readInt(sc, "Type (1-Full-Time, 2-Part-Time): ", 1, 2);
            String id = readText(sc, "Employee ID: ");
            while (findEmployee(employees, id) != null) {
                System.out.println("ID already exists!");
                id = readText(sc, "Employee ID: ");
            }
            String name = readText(sc, "Full name: ");
            String department = readText(sc, "Department: ");

            if (type == 1) {
                double baseSalary = readDouble(sc, "Base salary: ");
                double allowance = readDouble(sc, "Allowance: ");
                int workdays = readInt(sc, "Workdays (0-31): ", 0, 31);
                employees.add(new FullTimeEmployee(id, name, department, baseSalary, allowance, workdays));
            } else {
                double hours = readDouble(sc, "Hours worked: ");
                double rate = readDouble(sc, "Hourly rate: ");
                employees.add(new PartTimeEmployee(id, name, department, hours, rate));
            }
        }
    }

    // Input: employee
    // Output: prints the employee and salary, or Not found.
    // Approach: displayInfo() and calculateSalary() on an Employee reference
    private static void display(Employee employee) {
        if (employee == null) {
            System.out.println("Not found.");
            return;
        }
        employee.displayInfo();
        System.out.printf("Salary: %,.2f VND%n", employee.calculateSalary());
    }

    // Input: employees
    // Output: prints every employee and salary, or Not found. if empty
    // Approach: overload of display(), loop and call display(employee)
    private static void display(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("Not found.");
        }
        for (Employee e : employees) {
            System.out.println();
            display(e);
        }
    }

    // Input: employees (not empty)
    // Output: prints total payroll and highest salary employee
    // Approach: total(), then findMax()
    private static void printStatistics(ArrayList<Employee> employees) {
        System.out.printf("Total payroll: %,.2f VND%n", total(employees));
        System.out.println("\nHighest salary:");
        display(findMax(employees));
    }

    // Input: none
    // Output: prints the menu
    // Approach: one line per option, 0 exits
    private static void printMenu() {
        System.out.println("\n=== Payroll Management ===");
        System.out.println("1. Add employees");
        System.out.println("2. Display payroll");
        System.out.println("3. Find by ID");
        System.out.println("4. Find by name and department");
        System.out.println("5. Find by department");
        System.out.println("6. Statistics");
        System.out.println("0. Exit");
    }

    // Input: menu choices and data from keyboard
    // Output: result of each action
    // Approach: menu loop, switch on the choice; every action except add needs a non-empty list
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();

        while (true) {
            printMenu();
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
                    display(employees);
                    break;
                case 3:
                    display(findEmployee(employees, readText(sc, "Employee ID: ")));
                    break;
                case 4:
                    String name = readText(sc, "Full name: ");
                    String department = readText(sc, "Department: ");
                    display(findEmployee(employees, name, department));
                    break;
                case 5:
                    display(findByDepartment(employees, readText(sc, "Department: ")));
                    break;
                case 6:
                    printStatistics(employees);
                    break;
            }
        }

        sc.close();
    }
}
