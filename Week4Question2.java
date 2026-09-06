class EmployeeM2 {
    String empId;
    double salary;

    public EmployeeM2(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    public void printFinalSalary() {
        System.out.println(empId + " | Final Salary: Rs " + salary);
    }
}

public class Week4Question2 {
    public static void main(String[] args) {
        EmployeeM2[] employees = {
            new EmployeeM2("E-101", 40000),
            new EmployeeM2("E-102", 55000),
            new EmployeeM2("E-103", 62000),
            new EmployeeM2("E-104", 48000)
        };

        for (EmployeeM2 emp : employees) {
            emp.raiseSalary(5000);
            emp.printFinalSalary();
        }
    }
}
