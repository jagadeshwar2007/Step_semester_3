class MovieTicket {

    private final String seatNumber;
    final String screenId;
    protected final double ticketPrice;
    public final String movieTitle;

    public MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}

class AccessChecker {

    private AccessChecker() { }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            throw new IllegalArgumentException("modifier and context must not be null");
        }
        if (!(accessorContext.equals("SAME_CLASS")
                || accessorContext.equals("SAME_PACKAGE")
                || accessorContext.equals("DIFFERENT_PACKAGE"))) {
            throw new IllegalArgumentException("Unknown accessor context: " + accessorContext);
        }

        boolean allowed;
        switch (fieldModifier) {
            case "private":
                allowed = accessorContext.equals("SAME_CLASS");
                break;
            case "default":
                allowed = !accessorContext.equals("DIFFERENT_PACKAGE");
                break;
            case "protected":

                allowed = !accessorContext.equals("DIFFERENT_PACKAGE");
                break;
            case "public":
                allowed = true;
                break;
            default:
                throw new IllegalArgumentException("Unknown modifier: " + fieldModifier);
        }
        return allowed ? "ALLOWED" : "DENIED";
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt == null || attempt.length != 2) {
                    throw new IllegalArgumentException("Each attempt must be {modifier, context}");
                }
                if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                    allowed++;
                } else {
                    denied++;
                }
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

public class Week5Question1 {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeBatch(batch));

        MovieTicket t = new MovieTicket("A1", "S3", 250.0, "Inception");
        System.out.println(t.movieTitle + " | seat " + t.getSeatNumber() + " | screen " + t.screenId
                + " | price " + t.ticketPrice);
    }
}
