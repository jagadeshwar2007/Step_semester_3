import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected int number;

    public Room(int number) {
        this.number = number;
    }

    public abstract String getCategory();

    public abstract double ratePerNight();

    public double calculatePrice(int nights) {
        return ratePerNight() * nights;
    }

    public String getLabel() {
        return getCategory() + " Room " + number;
    }
}

class StandardRoom extends Room {
    public StandardRoom(int number) {
        super(number);
    }

    public String getCategory() {
        return "Standard";
    }

    public double ratePerNight() {
        return 100;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(int number) {
        super(number);
    }

    public String getCategory() {
        return "Deluxe";
    }

    public double ratePerNight() {
        return 180;
    }
}

class Suite extends Room {
    public Suite(int number) {
        super(number);
    }

    public String getCategory() {
        return "Suite";
    }

    public double ratePerNight() {
        return 300;
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

class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate start;
    private LocalDate end;
    private boolean cancelled = false;

    public Reservation(Customer customer, Room room, LocalDate start, LocalDate end) {
        this.customer = customer;
        this.room = room;
        this.start = start;
        this.end = end;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void markCancelled() {
        cancelled = true;
    }

    public LocalDate cancellationDeadline() {
        return start.minusDays(2);
    }

    public double getPrice() {
        return room.calculatePrice((int) ChronoUnit.DAYS.between(start, end));
    }
}

class HotelSystem {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MMM d");
    private List<Reservation> reservations = new ArrayList<>();

    private String period(LocalDate start, LocalDate end) {
        return FMT.format(start) + " to " + FMT.format(end);
    }

    private String shortPeriod(LocalDate start, LocalDate end) {
        return FMT.format(start) + "-" + end.getDayOfMonth();
    }

    public boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.getRoom() == room && !r.isCancelled() && start.isBefore(r.getEnd()) && end.isAfter(r.getStart())) {
                return false;
            }
        }
        return true;
    }

    public void checkAvailability(Room room, LocalDate start, LocalDate end) {
        if (isAvailable(room, start, end)) {
            System.out.println(room.getLabel() + " is available from " + period(start, end) + ".");
        } else {
            System.out.println(room.getLabel() + " is not available from " + period(start, end) + ".");
        }
    }

    public Reservation reserve(Customer customer, Room room, LocalDate start, LocalDate end) {
        if (!isAvailable(room, start, end)) {
            System.out.println(room.getLabel() + " is not available from " + period(start, end) + ".");
            return null;
        }
        Reservation reservation = new Reservation(customer, room, start, end);
        reservations.add(reservation);
        System.out.println("Reservation confirmed for " + customer.getName() + ", " + room.getLabel() + " ("
                + shortPeriod(start, end) + "). Price: $" + String.format("%.2f", reservation.getPrice()));
        return reservation;
    }

    public void cancel(Reservation reservation, LocalDate today) {
        if (reservation == null || reservation.isCancelled()) {
            System.out.println("No active reservation to cancel.");
            return;
        }
        String text = "Reservation for " + reservation.getCustomer().getName() + ", "
                + reservation.getRoom().getLabel() + " (" + shortPeriod(reservation.getStart(), reservation.getEnd())
                + ")";
        if (today.isAfter(reservation.cancellationDeadline())) {
            System.out.println(text + " cannot be cancelled, the deadline has passed.");
            return;
        }
        reservation.markCancelled();
        System.out.println(text + " cancelled successfully.");
    }
}

public class Week8Question4 {
    public static void main(String[] args) {
        HotelSystem hotel = new HotelSystem();
        Room standard101 = new StandardRoom(101);
        Room deluxe201 = new DeluxeRoom(201);
        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);
        LocalDate jan3 = LocalDate.of(2027, 1, 3);
        LocalDate jan7 = LocalDate.of(2027, 1, 7);

        hotel.checkAvailability(standard101, jan1, jan5);
        Reservation ra = hotel.reserve(a, standard101, jan1, jan5);
        hotel.reserve(b, standard101, jan3, jan7);
        hotel.cancel(ra, LocalDate.of(2026, 12, 20));
        hotel.reserve(c, deluxe201, LocalDate.of(2027, 2, 10), LocalDate.of(2027, 2, 12));
    }
}
