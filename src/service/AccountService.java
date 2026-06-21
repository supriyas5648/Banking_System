package service;

import dao.AccountDAO;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import models.Account;
import util.AccountNumberGenerator;
import util.InputValidator;

import java.util.List;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public Account createAccount(
            String name,
            String accountType,
            double initialBalance) {

        if (!InputValidator.isValidName(name)) {

            throw new IllegalArgumentException(
                    "Invalid Name"
            );
        }

        if (!InputValidator.isValidAmount(
                initialBalance)) {

            throw new IllegalArgumentException(
                    "Invalid Initial Balance"
            );
        }

        long accountNo =
                AccountNumberGenerator.generate();

        Account account =
                new Account(
                        accountNo,
                        name,
                        initialBalance,
                        accountType
                );

        boolean created =
                accountDAO.createAccount(
                        account
                );

        return created ? account : null;
    }

    public Account findAccount(
            long accountNo)
            throws AccountNotFoundException {

        Account account =
                accountDAO.findAccount(
                        accountNo
                );

        if (account == null) {

            throw new AccountNotFoundException(
                    "Account Number "
                            + accountNo
                            + " Not Found"
            );
        }

        return account;
    }

    public void deposit(
            long accountNo,
            double amount)
            throws AccountNotFoundException {

        if (!InputValidator.isValidAmount(
                amount)) {

            throw new IllegalArgumentException(
                    "Invalid Deposit Amount"
            );
        }

        Account account =
                findAccount(accountNo);

        double newBalance =
                account.getBalance() + amount;

        accountDAO.updateBalance(
                accountNo,
                newBalance
        );
    }

    public void withdraw(
            long accountNo,
            double amount)
            throws AccountNotFoundException,
                   InsufficientBalanceException {

        if (!InputValidator.isValidAmount(
                amount)) {

            throw new IllegalArgumentException(
                    "Invalid Withdrawal Amount"
            );
        }

        Account account =
                findAccount(accountNo);

        if (account.getBalance() < amount) {

            throw new InsufficientBalanceException(
                    "Available Balance : ₹"
                            + account.getBalance()
            );
        }

        double newBalance =
                account.getBalance() - amount;

        accountDAO.updateBalance(
                accountNo,
                newBalance
        );
    }

    public void transfer(
            long fromAccount,
            long toAccount,
            double amount)
            throws AccountNotFoundException,
                   InsufficientBalanceException {

        Account sender =
                findAccount(fromAccount);

        Account receiver =
                findAccount(toAccount);

        if (sender.getBalance() < amount) {

            throw new InsufficientBalanceException(
                    "Insufficient Balance"
            );
        }

        accountDAO.updateBalance(
                fromAccount,
                sender.getBalance() - amount
        );

        accountDAO.updateBalance(
                toAccount,
                receiver.getBalance() + amount
        );
    }

    public double checkBalance(
            long accountNo)
            throws AccountNotFoundException {

        return findAccount(
                accountNo
        ).getBalance();
    }

    public boolean deleteAccount(
            long accountNo)
            throws AccountNotFoundException {

        findAccount(accountNo);

        return accountDAO.deleteAccount(
                accountNo
        );
    }

    public List<Account> getAllAccounts() {

        return accountDAO.getAllAccounts();
    }
}