package sample;


/**
 Implement Checking object that extends Account since it is a type of account
 Inherits all properties of Account and checks if the account can directDeposit
 @author Shyam Patel, Drew Taylor
 */
public class Checking extends Account
{
    private boolean directDeposit;
    /**
     Constructor to create a Checking Account
     * @param holder Profile of the account holder
     * @param balance of the new account
     * @param dateOpen Opening date of account
     * @param directDeposit is allowed on the account
     */
    public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit)
    {
        super(holder, balance, dateOpen);
        this.directDeposit = directDeposit;
    }
    /**
     Calculates monthly interest earned in a checking account
     @Override monthlyInterest in abstract class Account
     @return monthly interest of a Checking account
     */
    public double monthlyInterest()
    {
        double checkingAnnualRate = 0.0005;
        double checkingMonthlyRate = checkingAnnualRate / 12;
        return this.getBalance() * checkingMonthlyRate; //needs to be completed
    }
    /**
     Calculates the monthly fee of owning a checking account
     @Override monthlyFee in abstract class Account
     @return monthly fee of a checking account
     */
    public double monthlyFee()
    {
        double monthlyFee = 25;
        double minBalanceToWaive = 1500;
        if (this.getBalance() >= minBalanceToWaive || this.getDirectDeposit())
            return 0;
        return monthlyFee;
    }
    /**
     Getter method to get value of directDeposit
     @return whether an account can directDeposit or not
     */
    public boolean getDirectDeposit() {
        return directDeposit;
    }
}