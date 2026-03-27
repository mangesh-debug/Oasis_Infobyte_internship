import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accNumber;
    private String pin;
    private double balance;

    public Account(String accNumber, String pin, double balance) {
        this.accNumber = accNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccNumber() { return accNumber; }
    public boolean checkPin(String p) { return pin.equals(p); }
    public double getBalance() { return balance; }
    public void deposit(double amt) { balance += amt; }
    public boolean withdraw(double amt) {
        if (amt <= 0 || amt > balance) return false;
        balance -= amt;
        return true;
    }
}

public class SimpleATM {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // sample accounts (accNumber, pin, balance)
        accounts.put("1001", new Account("1001", "1234", 5000.0));
        accounts.put("1002", new Account("1002", "4321", 2500.0));

        System.out.println("🏧 Welcome to Simple ATM");

        System.out.print("Enter account number: ");
        String acc = sc.nextLine().trim();

        Account account = accounts.get(acc);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine().trim();
        if (!account.checkPin(pin)) {
            System.out.println("Invalid PIN. Exiting.");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\nSelect option:");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.printf("Balance: ₹%.2f%n", account.getBalance());
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    double dep = Double.parseDouble(sc.nextLine());
                    account.deposit(dep);
                    System.out.println("Deposit successful.");
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: ");
                    double w = Double.parseDouble(sc.nextLine());
                    if (account.withdraw(w)) {
                        System.out.println("Please collect your cash.");
                    } else {
                        System.out.println("Insufficient funds or invalid amount.");
                    }
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you! Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}