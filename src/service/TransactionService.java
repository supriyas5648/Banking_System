package service;

import dao.TransactionsDAO;
import models.Transactions;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {

    private TransactionsDAO transactionDAO;

    public TransactionService() {
        transactionDAO = new TransactionsDAO();
    }

    public void recordTransaction(
            long accountNo,
            String transactionType,
            double amount) {

        Transactions transaction =
                new Transactions(
                        0,
                        accountNo,
                        transactionType,
                        amount,
                        LocalDateTime.now()
                );

        transactionDAO.saveTransaction(
                transaction
        );
    }

    public List<Transactions> getTransactionHistory(
            long accountNo) {

        return transactionDAO
                .getTransactionsByAccount(
                        accountNo
                );
    }

    public List<Transactions> getAllTransactions() {

        return transactionDAO
                .getAllTransactions();
    }
}