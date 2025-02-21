import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private double balance;
    private String accountHolderName;
    private ArrayList<String> transactions;

    public BankAccount(double initialBalance, String name) {
        balance = initialBalance;
        accountHolderName = name;
        transactions = new ArrayList<>();
        transactions.add("Initial deposit: " + initialBalance);
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }

    public void printTransactions() {
        System.out.println("Transaction History for " + accountHolderName + ":");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount account) {
        bankAccount = account;
    }

    public void checkBalance() {
        System.out.println("Current balance: " + bankAccount.getBalance());
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public void withdraw(double amount) {
        bankAccount.withdraw(amount);
    }

    public void printTransactions() {
        bankAccount.printTransactions();
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        
        // Simulate User Login (PIN-based)
        System.out.print("Enter your PIN to access the ATM: ");
        int pin = scanner.nextInt();
        
        if (pin != 1234) {  // Assuming 1234 is the correct PIN for simplicity
            System.out.println("Invalid PIN. Exiting...");
            return;
        }

        // Account Creation: You can change these to be input-driven if needed
        scanner.nextLine();  // Consume newline character left by nextInt()
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial account balance: ");
        double initialBalance = scanner.nextDouble();
        
        // Create bank account and ATM objects
        BankAccount bankAccount = new BankAccount(initialBalance, name);
        ATM atm = new ATM(bankAccount);
        
        while (true) {
            // Display ATM menu options
            System.out.println("\nATM Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.withdraw(withdrawalAmount);
                    break;
                case 4:
                    atm.printTransactions();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;  // Exits the program
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
