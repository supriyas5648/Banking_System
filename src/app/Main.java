package app;

import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import models.Account;
import models.Transactions;
import models.User;
import service.AccountService;
import service.AuthService;
import service.TransactionService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AuthService authService =
                new AuthService();

        AccountService accountService =
                new AccountService();

        TransactionService transactionService =
                new TransactionService();

        System.out.println("=================================");
        System.out.println("   BANKING MANAGEMENT SYSTEM");
        System.out.println("=================================");

        while (true) {

            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: 

                    System.out.print("Username : " );
                    String username = sc.nextLine();

                    System.out.print( "Password : " );
                    String password = sc.nextLine();

                    boolean registered =
                            authService.register(
                                    username,
                                    password,
                                    "CUSTOMER"
                            );

                    if (registered) {

                        System.out.println("Registration Successful");

                    } else {

                        System.out.println("Registration Failed");
                    }

                    break;

                case 2:

                    System.out.print("Username : " );
                    username = sc.nextLine();

                    System.out.print( "Password : " );
                    password = sc.nextLine();
                    
                    User user =
                            authService.login(
                                    username,
                                    password
                            );

                    if (user == null) {

                        System.out.println("Invalid Credentials");

                        break;
                    }

                    System.out.println( "\nWelcome " + user.getUsername() );

                    boolean loggedIn = true;

                    while (loggedIn) {

                        System.out.println("\n===== ACCOUNT MENU =====");

                        System.out.println("1. Create Account" );

                        System.out.println("2. Deposit");

                        System.out.println("3. Withdraw");

                        System.out.println("4. Transfer");

                        System.out.println("5. Check Balance");

                        System.out.println("6. View All Accounts");

                        System.out.println("7. View Transactions");

                        System.out.println("8. Delete Account");

                        System.out.println("9. Logout");

                        System.out.print("Enter Choice : ");

                        int option = sc.nextInt();

                        switch (option) {

                            case 1:

                                sc.nextLine();

                                System.out.print("Account Holder Name : ");

                                String name = sc.nextLine();

                                System.out.print("Account Type (Savings/Current) : ");

                                String type = sc.nextLine();

                                System.out.print("Initial Balance : ");

                                double balance = sc.nextDouble();

                                Account account =accountService.createAccount(
                                                        name,
                                                        type,
                                                        balance
                                                );

                                if (account != null) {

                                    System.out.println("\nAccount Created Successfully");

                                    System.out.println(account);
                                }

                                break;

                            case 2:

                                System.out.print("Account Number : ");

                                long depositAcc = sc.nextLong();

                                System.out.print("Amount : ");

                                double depositAmount = sc.nextDouble();

                                try {

                                    accountService.deposit(
                                            depositAcc,
                                            depositAmount
                                    );

                                    System.out.println("Deposit Successful");

                                } catch (Exception e) {

                                    System.out.println(e.getMessage());
                                }

                                break;

                            case 3:

                                System.out.print("Account Number : " );

                                long withdrawAcc =
                                        sc.nextLong();

                                System.out.print(
                                        "Amount : "
                                );

                                double withdrawAmount =
                                        sc.nextDouble();

                                try {

                                    accountService.withdraw(
                                            withdrawAcc,
                                            withdrawAmount
                                    );

                                    System.out.println(
                                            "Withdrawal Successful"
                                    );

                                } catch (
                                        AccountNotFoundException |
                                        InsufficientBalanceException e) {

                                    System.out.println(
                                            e.getMessage()
                                    );
                                }

                                break;

                            case 4:

                                System.out.print(
                                        "Sender Account : "
                                );

                                long sender =
                                        sc.nextLong();

                                System.out.print(
                                        "Receiver Account : "
                                );

                                long receiver =
                                        sc.nextLong();

                                System.out.print(
                                        "Amount : "
                                );

                                double transferAmount =
                                        sc.nextDouble();

                                try {

                                    accountService.transfer(
                                            sender,
                                            receiver,
                                            transferAmount
                                    );

                                    System.out.println(
                                            "Transfer Successful"
                                    );

                                } catch (
                                        AccountNotFoundException |
                                        InsufficientBalanceException e) {

                                    System.out.println(
                                            e.getMessage()
                                    );
                                }

                                break;

                            case 5:

                                System.out.print(
                                        "Account Number : "
                                );

                                long balanceAcc =
                                        sc.nextLong();

                                try {

                                    double currentBalance =
                                            accountService
                                                    .checkBalance(
                                                            balanceAcc
                                                    );

                                    System.out.println(
                                            "Current Balance : ₹"
                                                    + currentBalance
                                    );

                                } catch (
                                        AccountNotFoundException e) {

                                    System.out.println(
                                            e.getMessage()
                                    );
                                }

                                break;

                            case 6:

                                List<Account> accounts =
                                        accountService
                                                .getAllAccounts();

                                for (Account a : accounts) {

                                    System.out.println(
                                            "\n-------------------"
                                    );

                                    System.out.println(
                                            a
                                    );
                                }

                                break;

                            case 7:

                                System.out.print(
                                        "Account Number : "
                                );

                                long transactionAcc =
                                        sc.nextLong();

                                List<Transactions> transactions =
                                        transactionService
                                                .getTransactionHistory(
                                                        transactionAcc
                                                );

                                for (Transactions t : transactions) {

                                    System.out.println(
                                            "\n-------------------"
                                    );

                                    System.out.println(
                                            t
                                    );
                                }

                                break;

                            case 8:

                                System.out.print(
                                        "Account Number : "
                                );

                                long deleteAcc =
                                        sc.nextLong();

                                try {

                                    boolean deleted =
                                            accountService
                                                    .deleteAccount(
                                                            deleteAcc
                                                    );

                                    if (deleted) {

                                        System.out.println(
                                                "Account Deleted"
                                        );
                                    }

                                } catch (
                                        AccountNotFoundException e) {

                                    System.out.println(
                                            e.getMessage()
                                    );
                                }

                                break;

                            case 9:

                                loggedIn = false;

                                System.out.println(
                                        "Logged Out"
                                );

                                break;

                            default:

                                System.out.println(
                                        "Invalid Choice"
                                );
                        }
                    }

                    break;

                case 3:

                    System.out.println(
                            "Thank You"
                    );

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid Choice"
                    );
            }
        }
    }
}