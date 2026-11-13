import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface NotificationChannel {
    void send(Student student, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[Email → " + student.getName() + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[SMS → " + student.getName() + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[App → " + student.getName() + "] " + message);
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels = new ArrayList<>();

    public Student(String name, String department, NotificationChannel... preferred) {
        this.name = name;
        this.department = department;
        this.channels.addAll(Arrays.asList(preferred));
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {
    private String title;
    private List<String> departments;

    public Notice(String title, String... departments) {
        this.title = title;
        this.departments = Arrays.asList(departments);
    }

    public String validate() {
        if (title == null || title.trim().isEmpty()) {
            return "A title is required";
        }
        if (departments.isEmpty()) {
            return "At least one target department is required";
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void post(Notice notice) {
        String error = notice.validate();
        if (error != null) {
            System.out.println("Cannot post notice: " + error + ".");
            return;
        }
        System.out.println("Notice '" + notice.getTitle() + "' posted to " + String.join(", ", notice.getDepartments())
                + ".");
        for (Student student : students) {
            if (notice.getDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student, notice.getTitle());
                }
            }
        }
    }
}

public class Week8AssignmentQuestion5 {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();
        board.addStudent(new Student("Asha", "CSE", new EmailChannel(), new AppChannel()));
        board.addStudent(new Student("Ravi", "ECE", new SmsChannel()));

        board.post(new Notice("Lab Closed Tomorrow", "CSE"));
        board.post(new Notice("Fee Deadline Extended", "CSE", "ECE"));
        board.post(new Notice("Sports Day"));
    }
}
