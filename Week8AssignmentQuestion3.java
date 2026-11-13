import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class Seat {
    protected String id;

    public Seat(String id) {
        this.id = id;
    }

    public abstract double getPrice();

    public String getId() {
        return id;
    }
}

class RegularSeat extends Seat {
    public RegularSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {
    private String name;
    private boolean started = false;
    private Set<String> bookedSeats = new HashSet<>();

    public Show(String name) {
        this.name = name;
    }

    public boolean isBooked(String seatId) {
        return bookedSeats.contains(seatId);
    }

    public void markBooked(String seatId) {
        bookedSeats.add(seatId);
    }

    public void release(String seatId) {
        bookedSeats.remove(seatId);
    }

    public boolean hasStarted() {
        return started;
    }

    public void start() {
        started = true;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled = false;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }

    public double getTotal() {
        double total = 0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }
        return total;
    }

    public String seatList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(seats.get(i).getId());
        }
        return sb.toString();
    }

    public void cancel() {
        if (cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }
        if (show.hasStarted()) {
            System.out.println("Cannot cancel " + customer.getName() + "'s booking: the show has already started.");
            return;
        }
        cancelled = true;
        for (Seat seat : seats) {
            show.release(seat.getId());
        }
        System.out.println(customer.getName() + "'s booking cancelled. Seats " + seatList() + " released.");
    }

    public Customer getCustomer() {
        return customer;
    }
}

class BookingSystem {
    private static final int MAX_SEATS = 6;

    public Booking book(Customer customer, Show show, Seat... seats) {
        if (seats.length == 0 || seats.length > MAX_SEATS) {
            System.out.println("A booking must have between 1 and " + MAX_SEATS + " seats.");
            return null;
        }
        for (Seat seat : seats) {
            if (show.isBooked(seat.getId())) {
                System.out.println("Seat " + seat.getId() + " is already booked for this show.");
                return null;
            }
        }
        List<Seat> chosen = new ArrayList<>();
        for (Seat seat : seats) {
            show.markBooked(seat.getId());
            chosen.add(seat);
        }
        Booking booking = new Booking(customer, show, chosen);
        System.out.println("Booking confirmed for " + customer.getName() + ": " + booking.seatList() + ". Total: ₹"
                + String.format("%.2f", booking.getTotal()) + ".");
        return booking;
    }
}

public class Week8AssignmentQuestion3 {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        Show show = new Show("7 PM show");
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking = system.book(asha, show, new RegularSeat("A1"), new RegularSeat("A2"),
                new PremiumSeat("F5"));
        system.book(ravi, show, new RegularSeat("A2"));
        system.book(ravi, show, new ReclinerSeat("R1"));
        ashaBooking.cancel();
        system.book(neha, show, new RegularSeat("A2"));
    }
}
