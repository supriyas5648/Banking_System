package models;

import java.time.LocalDateTime;

public class Transactions {

    private int transactionId;
    private long accountNo;
    private String transactionType;
    private double amount;
    private LocalDateTime transactionTime;

    public Transactions() {
    }

    public Transactions(int transactionId,
                       long accountNo,
                       String transactionType,
                       double amount,
                       LocalDateTime transactionTime) {

        this.transactionId = transactionId;
        this.accountNo = accountNo;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(
            LocalDateTime transactionTime) {

        this.transactionTime = transactionTime;
    }

    @Override
    public String toString() {

        return "\nTransaction ID : " + transactionId +
               "\nAccount No     : " + accountNo +
               "\nType           : " + transactionType +
               "\nAmount         : ₹" + amount +
               "\nDate & Time    : " + transactionTime;
    }
}