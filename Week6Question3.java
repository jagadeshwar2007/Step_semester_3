import java.util.Arrays;

class LibraryMember {
    private final String memberId;
    private final int borrowLimit;
    private int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;
    private int totalFine = 0;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("memberId must be at least 4 non-blank characters");
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

    protected void chargeFine(int amount) {
        if (fineCount == fineHistory.length) {
            fineHistory = Arrays.copyOf(fineHistory, fineHistory.length * 2);
        }
        fineHistory[fineCount++] = amount;
        totalFine += amount;
    }

    int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    int getTotalFine() {
        return totalFine;
    }
}

class StudentMember extends LibraryMember {
    private final String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class Week6Question3 {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(s.getFineHistory()));

        LibraryMember plain = new LibraryMember("LB100", 3);
        plain.chargeFine(40);
        plain.chargeFine(10);
        System.out.println(plain.getTotalFine() + " " + Arrays.toString(plain.getFineHistory()));
    }
}
