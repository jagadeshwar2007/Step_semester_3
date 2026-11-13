abstract class WashType {
    public abstract String getName();

    public abstract int getDuration();

    public abstract double getCharge();
}

class QuickWash extends WashType {
    public String getName() {
        return "Quick";
    }

    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }
}

class NormalWash extends WashType {
    public String getName() {
        return "Normal";
    }

    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }
}

class HeavyWash extends WashType {
    public String getName() {
        return "Heavy";
    }

    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public double getCharge() {
        return washType.getCharge();
    }

    public WashingMachine getMachine() {
        return machine;
    }
}

class WashingMachine {
    private String id;
    private boolean busy = false;
    private WashCycle currentCycle;

    public WashingMachine(String id) {
        this.id = id;
    }

    public boolean isBusy() {
        return busy;
    }

    public WashCycle startWash(Student student, WashType washType) {
        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
            return null;
        }
        busy = true;
        currentCycle = new WashCycle(student, this, washType);
        System.out.println(washType.getName() + " wash started on " + id + " for " + student.getName() + " ("
                + washType.getDuration() + " min). Charge: ₹" + String.format("%.2f", currentCycle.getCharge())
                + ".");
        return currentCycle;
    }

    public void completeWash() {
        if (!busy) {
            System.out.println(id + " has no wash in progress.");
            return;
        }
        busy = false;
        currentCycle = null;
        System.out.println(id + " cycle completed. " + id + " is now free.");
    }
}

public class Week8AssignmentQuestion1 {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        m1.completeWash();
        m1.startWash(neha, new NormalWash());
    }
}
