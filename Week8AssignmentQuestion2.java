import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected int maxMarks;
    protected LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public abstract double penaltyPerDay();

    public int getLateDays(LocalDate submittedOn) {
        long days = ChronoUnit.DAYS.between(dueDate, submittedOn);
        return days > 0 ? (int) days : 0;
    }

    public double applyPenalty(double marks, int lateDays) {
        double result = marks * (1 - penaltyPerDay() * lateDays);
        return result < 0 ? 0 : result;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double penaltyPerDay() {
        return 0.10;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double penaltyPerDay() {
        return 0.20;
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

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submittedOn;
    private int lateDays;
    private boolean graded = false;

    public Submission(Student student, Assignment assignment, LocalDate submittedOn) {
        this.student = student;
        this.assignment = assignment;
        this.submittedOn = submittedOn;
        this.lateDays = assignment.getLateDays(submittedOn);
        String when = lateDays == 0 ? "on time" : lateDays + " days late";
        System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + when
                + "). Status: Submitted.");
    }

    public void resubmit(LocalDate newDate) {
        if (graded) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return;
        }
        submittedOn = newDate;
        lateDays = assignment.getLateDays(newDate);
        System.out.println(student.getName() + " resubmitted '" + assignment.getTitle() + "'. Status: Submitted.");
    }

    public void grade(double awarded) {
        if (graded) {
            System.out.println("'" + assignment.getTitle() + "' has already been graded.");
            return;
        }
        double finalMarks = assignment.applyPenalty(awarded, lateDays);
        graded = true;
        String text = student.getName() + " graded: " + show(finalMarks) + "/" + assignment.getMaxMarks();
        if (lateDays > 0) {
            int percent = (int) Math.round(assignment.penaltyPerDay() * lateDays * 100);
            text += " after " + percent + "% late penalty";
        }
        System.out.println(text + ". Status: Graded.");
    }

    private String show(double marks) {
        if (marks == Math.floor(marks)) {
            return String.valueOf((int) marks);
        }
        return String.format("%.1f", marks);
    }
}

public class Week8AssignmentQuestion2 {
    public static void main(String[] args) {
        Assignment linkedList = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2027, 3, 10));
        Assignment essay = new WrittenAssignment("Design Essay", 50, LocalDate.of(2027, 3, 12));
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSub = new Submission(asha, linkedList, LocalDate.of(2027, 3, 10));
        Submission raviSub = new Submission(ravi, essay, LocalDate.of(2027, 3, 14));
        ashaSub.grade(45);
        raviSub.grade(40);
        ashaSub.resubmit(LocalDate.of(2027, 3, 11));
    }
}
