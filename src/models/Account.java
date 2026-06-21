package models;

public class Account {

    private long accountNo;
    private String accountHolderName;
    private double balance;
    private String accountType;

    public Account() {
    }

    public Account(long accountNo,
                   String accountHolderName,
                   double balance,
                   String accountType) {

        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account No : " + accountNo +
                "\nName      : " + accountHolderName +
                "\nType      : " + accountType +
                "\nBalance   : ₹" + balance;
    }
}