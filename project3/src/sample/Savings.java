package sample;


/**
 Implement Savings object that extends Account since it is a type of account
 Inherits all properties of Account and checks if an account holder is loyal
 @author Shyam Patel, Drew Taylor
 */
public class Savings extends Account
{
    private boolean isLoyal;
    /**
     Constructor to create a Savings Account
     @param holder Profile of account holder
     @param balance starting balance of account
     @param dateOpen opening date of account
     @param isLoyal checks if account holder is loyal
     */
    public Savings(Profile holder, double balance, Date dateOpen, boolean isLoyal)
    {
        super(holder, balance, dateOpen);
        this.isLoyal = isLoyal;
    }
    /**
     Calculate the monthly interest of a savings account
     @Override monthlyInterest in abstract class Account
     @return monthly interest of a savings account
     */
    public double monthlyInterest()
    {
        double savingsAnnualRate = 0.0025;
        double loyalAnnualRate = 0.0035;
        double savingsMonthlyRate = savingsAnnualRate / 12;
        double loyalMonthlyRate = loyalAnnualRate / 12;
        return this.getIsLoyal() ? this.getBalance() * loyalMonthlyRate : this.getBalance() * savingsMonthlyRate; //needs to be completed
    }
    /**
     Calculate the monthly fee of a savings account
     @Override monthlyFee in abstract class Account
     @return monthly fee associated with a savings account
     */
    public double monthlyFee()
    {
        double monthlyFee = 5;
        double minBalanceToWaive = 300;
        if (this.getBalance() >= minBalanceToWaive)
            return 0;
        return monthlyFee;
    }
    /**
     Getter method to access isLoyal field of Savings object
     @return value of isLoyal filed 
     */
    public boolean getIsLoyal() {
        return isLoyal;
    }
}