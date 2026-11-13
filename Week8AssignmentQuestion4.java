abstract class MembershipPlan {
    protected static final double BASE_RATE = 1000;

    public abstract String getName();

    public abstract int getMonths();

    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {
    public String getName() {
        return "Monthly";
    }

    public int getMonths() {
        return 1;
    }

    public double calculateFee() {
        return BASE_RATE * getMonths();
    }
}

class QuarterlyPlan extends MembershipPlan {
    public String getName() {
        return "Quarterly";
    }

    public int getMonths() {
        return 3;
    }

    public double calculateFee() {
        return BASE_RATE * getMonths() * 0.90;
    }
}

class AnnualPlan extends MembershipPlan {
    public String getName() {
        return "Annual";
    }

    public int getMonths() {
        return 12;
    }

    public double calculateFee() {
        return BASE_RATE * getMonths() * 0.75;
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private String status = "Active";

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        System.out.println(plan.getName() + " membership created for " + member.getName() + ". Fee: ₹"
                + String.format("%,.2f", plan.calculateFee()) + ". Status: " + status + ".");
    }

    public void checkIn() {
        if (status.equals("Active")) {
            System.out.println(member.getName() + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + status + ".");
        }
    }

    public void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";
            System.out.println(member.getName() + "'s membership frozen. Status: " + status + ".");
        } else if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
        } else {
            System.out.println("Membership is already Frozen.");
        }
    }

    public void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member.getName() + "'s membership unfrozen. Status: " + status + ".");
        } else if (status.equals("Expired")) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else {
            System.out.println("Membership is not Frozen.");
        }
    }

    public void expire() {
        status = "Expired";
        System.out.println(member.getName() + "'s membership expired. Status: " + status + ".");
    }

    public String getStatus() {
        return status;
    }
}

public class Week8AssignmentQuestion4 {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();
        raviMembership.expire();
        raviMembership.freeze();
    }
}
