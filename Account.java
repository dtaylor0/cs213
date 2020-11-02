package sample;

/**
 Account abstract class that gives different accounts their attributes and methods 
 @author Shyam Patel, Drew Taylor
 */
public abstract class Account
{
    private Profile holder;
    private double balance;
    private Date dateOpen;
    
    /**
     Account constructor that creates a new Account with the inputted profile's holder, balance, and dateOpen.
     @param holder the account holder's first and last name
     @param balance the starting balance of the account
     @param dateOpen the opening date of the account
     */
    public Account(Profile holder, double balance, Date dateOpen)
    {
        this.holder = holder;
        this.balance = balance;
        this.dateOpen = dateOpen;
    }

    /**
     Decrease the balance by "amount"
     @param amount the number to decrease the balance by
     */
    //decrease the balance by amount
    public void debit(double amount) 
    {
        this.balance -= amount;
    }
    /**
     Increase the balance by "amount"
     @param amount the number to increase the balance by
     */
    //increase the balance by amount
    public void credit(double amount) {
        this.balance += amount;
    }
    
    /**
     * Regex string modifier to add comma's to number
     * @param num String to add comma's to 
     * @return replace the string with comma's between each 3 digits
     */
    private String regexCommafy(String num)
    {
        String regex = "(\\d)(?=(\\d{3})+$)";
        String [] splitNum = num.split("\\.");
        if(splitNum.length == 2)
            return splitNum[0].replaceAll(regex, "$1,") + "." + splitNum[1];
        else
            return num.replaceAll(regex, "$1,");
    }
    
    /**
     toString method to print out the fields of the class
     @Override override toString of Object class
     @return fields of Account class as a string
     */
    public String toString()
    {
        String className = this.getClass().getSimpleName();
        if (className.equals("MoneyMarket"))
            className = "Money Market";
        String res =  "*" + className
                      + "*" + holder.getFName()
                      + " " + holder.getLName()
                      + "* $" + regexCommafy(String.format("%.2f", balance))
                      + "*" + dateOpen.toString();
        if (this instanceof Checking)
            res += (((Checking) this).getDirectDeposit() ? "*direct deposit account*" : "");
        else if (this instanceof Savings)
            res += (((Savings) this).getIsLoyal() ? "*special Savings account*" : "");
        else if (this instanceof MoneyMarket)
            res += "*" + ((MoneyMarket) this).getWithdrawals() + " withdrawals*";
        return res;
    }
    /**
     abstract method for different accounts to calculate monthlyInterest
     @return an accounts monthlyInterest
     */
    public abstract double monthlyInterest();
    /**
     abstract method to calculate the monthlyFee of different accounts
     @return an accounts monthlyFee
     */
    public abstract double monthlyFee();

    /**
     Getter method to get the opening date
     @return dateOpen of account
     */
    public Date getDateOpen() 
    {
        return dateOpen;
    }

    /**
     Getter method to get the balance
     @return balance of account
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     Getter method to return the Profile of the account holder
     @return holder of the account
     */
    public Profile getHolder()
    {
        return holder;
    }

    /**
     Checks if two accounts are equal
     @Override Object class equals method
     @param account to compare if they are equal
     @return true if equal and false if not
     */
    public boolean equals(Account account) {
        return account.getHolder().equals(holder) && account.getClass().equals(this.getClass());
    }
}