import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    protected String name;
    protected boolean available = true;

    public Vehicle(String name) {
        this.name = name;
    }

    public abstract double ratePerDay();

    public double calculateCharge(int days) {
        return ratePerDay() * days;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Sedan extends Vehicle {
    public Sedan(String name) {
        super(name);
    }

    public double ratePerDay() {
        return 40;
    }
}

class SUV extends Vehicle {
    public SUV(String name) {
        super(name);
    }

    public double ratePerDay() {
        return 60;
    }
}

class Truck extends Vehicle {
    public Truck(String name) {
        super(name);
    }

    public double ratePerDay() {
        return 80;
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

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private double charge;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.charge = vehicle.calculateCharge(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public double getCharge() {
        return charge;
    }
}

class RentalSystem {
    private List<Rental> activeRentals = new ArrayList<>();

    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName() + " is currently unavailable.");
            return null;
        }
        vehicle.setAvailable(false);
        Rental rental = new Rental(vehicle, customer, days);
        activeRentals.add(rental);
        System.out.println(vehicle.getName() + " rented successfully by " + customer.getName()
                + ". Rental charge: $" + String.format("%.2f", rental.getCharge()));
        return rental;
    }

    public void returnVehicle(Rental rental) {
        if (rental == null || !activeRentals.remove(rental)) {
            System.out.println("No active rental to return.");
            return;
        }
        rental.getVehicle().setAvailable(true);
        System.out.println(rental.getVehicle().getName() + " returned by " + rental.getCustomer().getName() + ".");
    }
}

public class Week8Question1 {
    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");
        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Rental r1 = system.rentVehicle(c1, sedanA, 3);
        system.rentVehicle(c2, sedanA, 2);
        system.returnVehicle(r1);
        system.rentVehicle(c3, suvB, 5);
    }
}
