package sample;


/**
 Implement MoneyMarket object that extends Account since it is a type of account
 Inherits all properties of Account and keeps track of the number of withdrawals
 @author Shyam Patel, Drew Taylor
 */
public class MoneyMarket extends Account
{
    private int withdrawals;
    /**
     Constructor to create a MoneyMarket account
     @param holder Profile of the account holder
     @param balance starting balance of account
     @param dateOpen opening date of the account
     @param withdrawals number of withdrawals from the account
     */
    public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals)
    {
        super(holder, balance, dateOpen);
        this.withdrawals = withdrawals;
    }
    /**
     Calculate the monthly interest of a money market account
     @Override monthlyInterest in abstract class Account
     @return monthly interest fee of account
     */
    public double monthlyInterest()
    {
        double MMAnnualRate = 0.0065;
        double MMMonthlyRate = MMAnnualRate / 12;
        return this.getBalance() * MMMonthlyRate;
    }
    /**
     Calculate the fee of owning a Money Market account
     @Override monthlyFee in abstract class Account
     @return fee of associated account
     */
    public double monthlyFee()
    {
        double moneyMarketFee = 12;
        double minBalanceToWaive = 2500;
        if (withdrawals <= 6 && this.getBalance() >= minBalanceToWaive)
            return 0;
        return moneyMarketFee;
    }
    /**
     Getter to access the number of withdrawals from a money market account
     @return withdrawals field from MoneyMarket object
     */
    public int getWithdrawals() {
        return withdrawals;
    }

    /**
     Setter method to increment the number of withdrawals
     */
    public void incrementWithdrawals() {
        withdrawals++;
    }
}