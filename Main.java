class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;
    private int pin;

  
    public BankAccount(String accountNumber, String accountHolder,
                       double balance, int pin) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.pin = pin;
    }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

   
    public void withdraw(double amount, int enteredPin) {

        if (enteredPin != pin) {
            System.out.println("Invalid PIN");
        }
        else if (amount > balance) {
            System.out.println("Insufficient Balance");
        }
        else {
            balance -= amount;
            System.out.println("Withdrawal Successful");
        }

        System.out.println("Balance: " + balance);
    }

   
    public void checkBalance() {
        System.out.println("Balance: " + balance);
    }

   
    public void changePin(int oldPin, int newPin) {
        if (oldPin == pin) {
            pin = newPin;
            System.out.println("PIN changed successfully");
        }
        else {
            System.out.println("Invalid PIN");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        BankAccount account =
            new BankAccount("10001", "Ravi", 10000, 1234);

     
        account.withdraw(3000, 1234);

       
        BankAccount account2 =
            new BankAccount("10002", "Ravi", 10000, 1234);

        account2.withdraw(3000, 5555);

       
        BankAccount account3 =
            new BankAccount("10003", "Ravi", 2000, 1234);

        account3.withdraw(5000, 1234);
    }
}