class EmployeeM5 {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public EmployeeM5(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class Week3Question5 {
    public static void main(String[] args) {
        new EmployeeM5("Alice", 50000);
        new EmployeeM5("Bob", 60000);
        new EmployeeM5("Charlie", 70000);

        EmployeeM5.printCompanyInfo();
    }
}

