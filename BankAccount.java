import java.util.Scanner;

class BankAccount {

    String name;
    long accountNumber;
    double balance;

    BankAccount(String name, long accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        balance += amount;
        System.out.println("Amount deposited successfully.");
    }

    void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        balance -= amount;
        System.out.println("Amount withdrawn successfully.");
    }

    void balanceEnquiry() {
        System.out.println("Current Balance = ₹" + balance);
    }

    void displayDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

class BankManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount account = null;

        int choice;

        do {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Account Details");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            try {

                choice = sc.nextInt();

                switch (choice) {

                    case 1:

                        if (account != null) {
                            System.out.println("Account already exists.");
                            break;
                        }

                        sc.nextLine();

                        System.out.print("Enter name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter account number: ");
                        long accountNumber = sc.nextLong();

                        System.out.print("Enter initial balance: ");
                        double balance = sc.nextDouble();

                        if (balance < 0) {
                            throw new IllegalArgumentException(
                                    "Initial balance cannot be negative.");
                        }

                        account = new BankAccount(
                                name, accountNumber, balance);

                        System.out.println("Account created successfully.");

                        break;

                    case 2:

                        if (account == null) {
                            System.out.println("Please create an account first.");
                            break;
                        }

                        System.out.print("Enter deposit amount: ");
                        double deposit = sc.nextDouble();

                        account.deposit(deposit);

                        break;

                    case 3:

                        if (account == null) {
                            System.out.println("Please create an account first.");
                            break;
                        }

                        System.out.print("Enter withdrawal amount: ");
                        double withdraw = sc.nextDouble();

                        account.withdraw(withdraw);

                        break;

                    case 4:

                        if (account == null) {
                            System.out.println("Please create an account first.");
                            break;
                        }

                        account.balanceEnquiry();

                        break;

                    case 5:

                        if (account == null) {
                            System.out.println("Please create an account first.");
                            break;
                        }

                        account.displayDetails();

                        break;

                    case 6:

                        System.out.println("Thank you for using the Bank Management System.");
                        break;

                    default:

                        System.out.println("Invalid choice.");

                }

            } catch (java.util.InputMismatchException e) {

                System.out.println("Invalid input. Please enter the correct data type.");
                sc.nextLine();
                choice = 0;

            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
                choice = 0;
            }

        } while (choice != 6);

        sc.close();
    }
}