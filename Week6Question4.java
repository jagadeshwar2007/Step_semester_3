class LibraryMember {
    private final String memberId;
    private final int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 3) {
            throw new IllegalArgumentException("memberId too short");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private final String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    String getCourse() {
        return course;
    }

    String displayInfo() {
        return "Student | Course: " + course + " | Books: " + getBooksBorrowed();
    }
}

public class Week6Question4 {

    static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();
        for (LibraryMember m : members) {
            report.append(m.displayInfo());
            if (m instanceof StudentMember) {
                StudentMember sm = (StudentMember) m;
                report.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }
            report.append(" | ");
        }
        return report.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] members = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };
        System.out.println(batchPrint(members));

        try {
            LibraryMember plain = new LibraryMember("LB6", 3);
            StudentMember bad = (StudentMember) plain;
            System.out.println(bad);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
