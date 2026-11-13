enum LeaveStatus {
    PENDING, APPROVED, REJECTED;

    public String label() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract int maxLeaveDays();

    public boolean allowsLeave(int days) {
        return days <= maxLeaveDays();
    }

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    public int maxLeaveDays() {
        return 20;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    public int maxLeaveDays() {
        return 5;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    public int maxLeaveDays() {
        return 2;
    }
}

class LeaveRequest {
    private Employee employee;
    private String period;
    private int days;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(Employee employee, String period, int days) {
        this.employee = employee;
        this.period = period;
        this.days = days;
        System.out.println("Leave request submitted for " + employee.getName() + " (" + period + "). Status: "
                + status.label() + ".");
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getDays() {
        return days;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    void approve() {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status.label() + " to Approved.");
            return;
        }
        status = LeaveStatus.APPROVED;
        System.out.println(employee.getName() + "'s leave request (" + period + ") approved. Status: "
                + status.label() + ".");
    }

    void reject() {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status.label() + " to Rejected.");
            return;
        }
        status = LeaveStatus.REJECTED;
        System.out.println(employee.getName() + "'s leave request (" + period + ") rejected. Status: "
                + status.label() + ".");
    }

    public void changeStatus(LeaveStatus newStatus) {
        if (newStatus == LeaveStatus.PENDING || status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status.label() + " to "
                    + newStatus.label() + ".");
            return;
        }
        if (newStatus == LeaveStatus.APPROVED) {
            approve();
        } else {
            reject();
        }
    }
}

class Manager {
    private String name;

    public Manager(String name) {
        this.name = name;
    }

    public void review(LeaveRequest request, boolean approve) {
        if (approve && request.getEmployee().allowsLeave(request.getDays())) {
            request.approve();
        } else {
            request.reject();
        }
    }
}

public class Week8Question2 {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");
        Manager alice = new Manager("Alice");
        Manager bob = new Manager("Bob");

        LeaveRequest johnLeave = new LeaveRequest(john, "Jan 1-5", 5);
        alice.review(johnLeave, true);

        LeaveRequest janeLeave = new LeaveRequest(jane, "Feb 10-11", 2);
        bob.review(janeLeave, false);

        johnLeave.changeStatus(LeaveStatus.PENDING);
    }
}
