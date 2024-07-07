import java.awt.*;    
import java.awt.event.*;    
import java.util.HashMap;
import java.util.Scanner;

class Bank 
{
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Bank(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }

    public String getAccountHolder() 
    {
        return accountHolder;
    }

    public double getBalance() 
    {
        return balance;
    }

    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
        else 
        {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }
        else 
        {
            System.out.println("Invalid withdraw amount");
        }
    }
}

public class BankingSystem 
{
    private HashMap<String, Bank> accounts;

    public BankingSystem() 
    {
        accounts = new HashMap<>();
    }

    public void addAccount(String accountNumber, String accountHolder, double initialBalance) 
    {
        Bank newAccount = new Bank(accountNumber, accountHolder, initialBalance);
        accounts.put(accountNumber, newAccount);
    }

    public Bank getAccount(String accountNumber) 
    {
        return accounts.get(accountNumber);
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) 
    {
        Bank fromAccount = accounts.get(fromAccountNumber);
        Bank toAccount = accounts.get(toAccountNumber);

        if (fromAccount != null && toAccount != null) 
        {
            if (fromAccount.getBalance() >= amount) 
            {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transferred: " + amount + " from " + fromAccountNumber + " to " + toAccountNumber);
            } 
            else 
            {
                System.out.println("Insufficient funds in the source account.");
            }
   
        }
        else
        {
            System.out.println("One or both accounts do not exist.");
        }
    }

    public static void main(String[] args) 
    {
        BankingSystem system = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Account\n2. Deposit\n3. Withdraw\n4. Check Balance\n5. Transfer Funds\n6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accountHolder = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    system.addAccount(accountNumber, accountHolder, initialBalance);
                    System.out.println("Account created successfully.");
                    break;
                    
                    
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    Bank account = system.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;


                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    account = system.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter withdraw amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;


                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    account = system.getAccount(accountNumber);
                    if (account != null) {
                        System.out.println("Balance: " + account.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;


                case 5:
                    System.out.print("Enter source account number: ");
                    String fromAccountNumber = scanner.nextLine();
                    System.out.print("Enter destination account number: ");
                    String toAccountNumber = scanner.nextLine();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    system.transferFunds(fromAccountNumber, toAccountNumber, transferAmount);
                    break;


                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;


                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}