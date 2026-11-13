import java.util.ArrayList;
import java.util.List;

interface PaymentMethod {
    String getName();

    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    private double creditLimit;

    public CreditCardPayment(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getName() {
        return "Credit Card";
    }

    public boolean processPayment(double amount) {
        return amount <= creditLimit;
    }
}

class PayPalPayment implements PaymentMethod {
    private double balance;

    public PayPalPayment(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return "PayPal";
    }

    public boolean processPayment(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}

class BankTransferPayment implements PaymentMethod {
    private double accountBalance;

    public BankTransferPayment(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return "Bank Transfer";
    }

    public boolean processPayment(double amount) {
        if (amount > accountBalance) {
            return false;
        }
        accountBalance -= amount;
        return true;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
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

    public Order createOrder() {
        System.out.println("Order created for Customer " + name + ".");
        return new Order(this);
    }
}

class Order {
    private Customer customer;
    private List<Product> products = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();
    private boolean paid = false;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        products.add(product);
        quantities.add(quantity);
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getPrice() * quantities.get(i);
        }
        return total;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getStatus() {
        return paid ? "Paid" : "Pending";
    }

    public void pay(PaymentMethod method) {
        if (products.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }
        System.out.println("Payment initiated via " + method.getName() + " for Order " + customer.getName() + ".");
        if (method.processPayment(getTotal())) {
            paid = true;
            System.out.println("Payment for Order " + customer.getName() + " successful.");
        } else {
            System.out.println("Payment for Order " + customer.getName() + " failed.");
        }
        System.out.println("Order status: " + getStatus() + ".");
    }
}

public class Week8Question5 {
    public static void main(String[] args) {
        Product a = new Product("Product A", 25);
        Product b = new Product("Product B", 40);
        Product c = new Product("Product C", 60);

        Order orderX = new Customer("X").createOrder();
        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);
        orderX.pay(new CreditCardPayment(500));

        Order orderY = new Customer("Y").createOrder();
        orderY.pay(new CreditCardPayment(500));

        Order orderZ = new Customer("Z").createOrder();
        orderZ.addProduct(c, 1);
        orderZ.pay(new PayPalPayment(10));
    }
}
