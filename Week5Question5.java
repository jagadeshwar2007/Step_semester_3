import java.util.Arrays;

class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        if (bookingId == null) {
            throw new IllegalArgumentException("bookingId must not be null");
        }
        if (seatNumbers == null) {
            throw new IllegalArgumentException("seatNumbers must not be null");
        }
        this.bookingId = bookingId;
        this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public final String getBookingId() {
        return bookingId;
    }

    public final String[] getSeatNumbers() {
        return Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= seatNumbers.length) {
            throw new IndexOutOfBoundsException("seat index " + index + " out of range");
        }
        String[] copy = getSeatNumbers();
        copy[index] = newSeat;
        return new BookingReceipt(bookingId, copy);
    }
}

final class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        if (groupSize < 1) {
            throw new IllegalArgumentException("groupSize must be at least 1");
        }
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public GroupBookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updated = super.withUpdatedSeat(index, newSeat).getSeatNumbers();
        return new GroupBookingReceipt(getBookingId(), updated, groupSize);
    }
}

class NightlySettlement {

    private NightlySettlement() { }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int group = 0;
        int individual = 0;
        int nullSkipped = 0;

        if (receipts != null) {
            for (BookingReceipt r : receipts) {
                if (r == null) {
                    nullSkipped++;
                } else if (r instanceof GroupBookingReceipt) {
                    group++;
                } else {
                    individual++;
                }
            }
        }
        int processed = group + individual;
        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }
}

public class Week5Question5 {
    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println(b.getSeatNumbers()[0]);

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println(Arrays.toString(b.getSeatNumbers()));
        System.out.println(Arrays.toString(updated.getSeatNumbers()));

        BookingReceipt[] batch = {
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(NightlySettlement.processNightlySettlement(batch));

    }
}
