package ATM1;
import java.util.*;

// Base Class
class Account {

    protected int accNo;
    protected String name;
    protected double balance;

    // Constructor
    Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    // Deposit Method
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount Deposited Successfully.");
    }

    // Withdraw Method with Exception Handling
    public void withdraw(double amount) throws Exception {

        if (amount > balance) {
            throw new Exception("Insufficient Balance!");
        }

        balance -= amount;
        System.out.println("Amount Withdrawn Successfully.");
    }

    // Transfer Method
    public void transfer(Account receiver, double amount) throws Exception {

        if (amount > balance) {
            throw new Exception("Insufficient Balance for Transfer!");
        }

        balance -= amount;
        receiver.balance += amount;

        System.out.println("Transfer Successful.");
    }

    // Display Details
    public void display() {
        System.out.println("Account No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Balance: ₹" + balance);
    }
}

// Savings Account Class (Inheritance)
class SavingsAccount extends Account {

    SavingsAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }
}

// Current Account Class (Inheritance)
class CurrentAccount extends Account {

    CurrentAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }
}


public class ATMSystem
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ArrayList to store accounts
        ArrayList<Account> accounts = new ArrayList<>();

        int choice;

        do {

            System.out.println("\n--- Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Display Account");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            try {

                switch (choice) {

                    // Create Account
                    case 1:

                        System.out.print("Enter Account Number: ");
                        int accNo = sc.nextInt();

                        System.out.print("Enter Name: ");
                        String name = sc.next();

                        System.out.print("Enter Balance: ");
                        double bal = sc.nextDouble();

                        System.out.print("Account Type (1-Savings, 2-Current): ");
                        int type = sc.nextInt();

                        Account acc;

                        if (type == 1) {
                            acc = new SavingsAccount(accNo, name, bal);
                        } else {
                            acc = new CurrentAccount(accNo, name, bal);
                        }

                        accounts.add(acc);

                        System.out.println("Account Created Successfully.");
                        break;

                    // Deposit
                    case 2:

                        System.out.print("Enter Account Number: ");
                        int dAcc = sc.nextInt();

                        System.out.print("Enter Amount: ");
                        double dAmt = sc.nextDouble();

                        for (Account a : accounts) {

                            if (a.accNo == dAcc) {
                                a.deposit(dAmt);
                            }else {
                                System.out.println("Invalid acc no.");
                            }
                        }

                        break;

                    // Withdraw
                    case 3:

                        System.out.print("Enter Account Number: ");
                        int wAcc = sc.nextInt();

                        System.out.print("Enter Amount: ");
                        double wAmt = sc.nextDouble();

                        for (Account a : accounts) {

                            if (a.accNo == wAcc) {
                                a.withdraw(wAmt);
                            }else{
                                System.out.println("Invalid acc no.");
                            }
                        }

                        break;

                    // Transfer
                    case 4:

                        System.out.print("From Account Number: ");
                        int from = sc.nextInt();

                        System.out.print("To Account Number: ");
                        int to = sc.nextInt();

                        System.out.print("Enter Amount: ");
                        double tAmt = sc.nextDouble();

                        Account sender = null;
                        Account receiver = null;

                        for (Account a : accounts) {

                            if (a.accNo == from) {
                                sender = a;
                            }
                            if (a.accNo == to) {
                                receiver = a;
                            }
                        }

                        if (sender != null && receiver != null) {
                            sender.transfer(receiver, tAmt);
                        }

                        break;

                    // Display Account
                    case 5:

                        System.out.print("Enter Account Number: ");
                        int sAcc = sc.nextInt();

                        for (Account a : accounts) {

                            if (a.accNo == sAcc) {
                                a.display();
                            }
                        }

                        break;

                    case 6:
                        System.out.println("Thank You!");
                        break;

                    default:
                        System.out.println("Invalid Choice.");

                }

            }
            // Exception Handling
            catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }
}
