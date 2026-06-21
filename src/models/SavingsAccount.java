package models;

public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount() {
    }

    public SavingsAccount(long accountNo,
                          String accountHolderName,
                          double balance,
                          double interestRate) {

        super(accountNo,
              accountHolderName,
              balance,
              "Savings");

        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return getBalance() * interestRate / 100;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nInterest Rate : " + interestRate + "%";
    }
}