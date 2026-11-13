class LibraryMember {
    private static int membersEnrolled = 0;

    public final String memberNumber;
    private final int borrowLimit;
    private int booksBorrowed;
    private String lastGenre;

    public LibraryMember(int borrowLimit) {
        membersEnrolled++;
        this.memberNumber = "LIB-" + (100 + membersEnrolled);
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    void borrowBook(String genre) {
        this.lastGenre = genre;
        borrowBook();
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    String getLastGenre() {
        return lastGenre;
    }

    static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class FacultyMember extends LibraryMember {
    private final String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    String getDepartment() {
        return department;
    }
}

public class Week6Question5 {

    static String processNightlyAudit(LibraryMember[] members) {
        int faculty = 0;
        int regular = 0;
        int nullSkipped = 0;
        if (members != null) {
            for (LibraryMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else if (m instanceof FacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }
        int processed = faculty + regular;
        return processed + " processed | " + nullSkipped + " null skipped | "
                + faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] batch = {new FacultyMember(5, "Physics"), null, new LibraryMember(3)};
        System.out.println(processNightlyAudit(batch));

    }
}
