package sample;


/**
 Keeps track of the different accounts and is able to do modify each account as requested by user
 @author Shyam Patel, Drew Taylor
 */
public class AccountDatabase
{
    private Account[] accounts;
    private int size;

    /**
     Constructor that initializes and empty database of capacity 5
     */
    public AccountDatabase()
    {
        int initCapacity = 5;
        this.size = 0;
        this.accounts = new Account[initCapacity];
    }

    /**
     Finds the account in the database
     @param account to find in the database
     @return index of account
     */
    private int find(Account account)
    {
        for (int i = 0; i < size; i++) {
            if (account.equals(accounts[i]))
                return i;
        }
        return -1;
    }
    /**
     Makes the database grow in capacity by 5
     Used when database is full
     */
    private void grow()
    {
        int addedCapacity = 5;
        Account[] arr = new Account[accounts.length + addedCapacity];
        for (int i = 0; i < size; i++) {
            arr[i] = accounts[i];
        }
        accounts = arr;
    }
    /**
     Add account to database
     @param account to add to database
     @return true on successful add and false if failed
     */
    //return false if account exists
    public boolean add(Account account)
    {
        if (find(account) > -1) //already exists in database
            return false;
        if (accounts[accounts.length - 1] != null)
            grow();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                size++;
                return true;
            }
        }
        return false;
    }
    /**
     Remove account from the database
     @param account to remove
     @return true if remove was successful and false if not
     */
    //return false if account doesn�t exist
    public boolean remove(Account account)
    {
        int index = find(account);
        if (index < 0)
            return false;

        accounts[index] = null;
        if (index < size - 1) {
            accounts[index] = accounts[size - 1];
            accounts[size - 1] = null;
        }
        size--;
        return true;
    }
    /**
     Deposit some money into an account
     @param account to deposit into
     @param amount of money to deposit
     @return true if deposit was successful and false if deposit failed
     */
    public boolean deposit(Account account, double amount)
    {
        int index = find(account);
        if (index < 0)
            return false;
        accounts[index].credit(amount);
        return true;
    }
    /**
     Withdraw money from an account
     @param account to withdraw from
     @param amount of money to withdraw
     @return 0: withdrawal successful, 1: insufficient funds, -1 account doesn�t exist
     */
    public int withdrawal(Account account, double amount)
    {
        int index = find(account);
        if (index < 0)
            return -1;
        double bal = accounts[index].getBalance();
        if (bal < amount)
            return 1;
        accounts[index].debit(amount);
        if (accounts[index] instanceof MoneyMarket)
            ((MoneyMarket) accounts[index]).incrementWithdrawals();
        return 0;
    }
    /**
     Sort database based off of the opening date (ascending order)
     */
    private void sortByDateOpen()
    {
        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j > -1; j--) {
                if (accounts[j + 1].getDateOpen().compareTo(accounts[j].getDateOpen()) < 0)
                {
                    Account temp = accounts[j + 1];
                    accounts[j + 1] = accounts[j];
                    accounts[j] = temp;
                }
                else
                    break;
            }
        }
    }
    /**
     Sort database off of the last name of the account Profile (ascending order)
     */
    private void sortByLastName()
    {
        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j > -1; j--) {
                if (accounts[j + 1].getHolder().getLName().compareTo(accounts[j].getHolder().getLName()) < 0) {
                    Account temp = accounts[j + 1];
                    accounts[j + 1] = accounts[j];
                    accounts[j] = temp;
                }
                else
                    break;
            }
        }
    }

    /**
     Regex string modifier to add comma's to number
     @param num String to add comma's to 
     @return replace the string with comma's between each 3 digits
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
     Print each account in the database's interest, fee, and the new balance after calculations
     */
    private String printFeesAndInterest()
    {
        String res = "";
        for (int i = 0; i < size; i++)
        {
            Account currAccount = accounts[i];
            String Account = currAccount.toString() + "\n";
            double interest = currAccount.monthlyInterest();
            double fee = currAccount.monthlyFee();
            currAccount.credit(interest);
            currAccount.debit(fee);
            double updatedBalance = currAccount.getBalance();
            String inter = String.format("-interest: $ %.2f", interest) + "\n";
            String fees = String.format("-fee: $ %.2f", fee) + "\n";
            String balance = "-new balance: $ " + regexCommafy(String.format("%.2f", updatedBalance)) + "\n";
            res += Account + inter + fees + balance + "\n";
        }
        return res;
    }
    /**
     Print each account in the database by the opening date
     */
    public String printByDateOpen()
    {
        if (size <= 0)
            return "Database is empty.\n";
        sortByDateOpen();
        return "\n--Printing statements by date open--\n" + "\n" + printFeesAndInterest() + "--end of printing--\n";
    }
    /**
     Print each account in the database by the last name of the account's Profile
     */
    public String printByLastName()
    {
        if (size <= 0)
            return "Database is empty.\n";
        sortByLastName();
        return "\n--Printing statements by date open--\n" + "\n" + printFeesAndInterest() + "--end of printing--\n";
    }
    /**
     Print each account in the database with all their fields
     */
    public String printAccounts()
    {
        if (size <= 0)
            return "Database is empty.\n";
        String res = "--Listing accounts in the database--" + "\n";
        for (int i = 0; i < size; i++) {
            res += accounts[i].toString() + "\n";
        }
        res += "--end of listing--" + "\n";
        return res;
    }

    /**
     *Convert a database to a comma separated String, for writing to a txt file
     * @return String with contents of database written as comma separated values
     */
    public String convertToTxt()
    {
        String res = "";
        for (int i = 0; i < size; i++) {
            Account acct = accounts[i];
            if (acct instanceof Savings)
                res += "S,";
            else if (acct instanceof Checking)
                res += "C,";
            else if (acct instanceof MoneyMarket)
                res += "M,";
            Profile holder = acct.getHolder();
            res += holder.getFName() + ",";
            res += holder.getLName() + ",";
            res += String.format("%.2f", acct.getBalance()) + ",";
            res += acct.getDateOpen().toString() + ",";
            if (acct instanceof MoneyMarket) {
                res += String.valueOf(((MoneyMarket) acct).getWithdrawals());
            }
            else if (acct instanceof Savings) {
                res += String.valueOf(((Savings) acct).getIsLoyal());
            }
            else if (acct instanceof Checking) {
                res += String.valueOf(((Checking) acct).getDirectDeposit());
            }
            res += "\n";
        }
        return res;
    }

    /**
     Testbed main to test the database works properly
     @param args arguments to pass to run the class
     */
    public static void main(String[] args) {
        AccountDatabase db = new AccountDatabase();
        MoneyMarket acc1 = new MoneyMarket(new Profile("Drew", "Taylor"), 100.5522222, new Date(3, 3, 2019), 4);
        db.add(acc1);
        Checking acc2 = new Checking(new Profile("John", "Smith"), 1009.55, new Date(3, 31, 2013), true);
        db.add(acc2);
        Checking acc3 = new Checking(new Profile("Jay", "Adams"), 809.55, new Date(3, 21, 2017), false);
        db.add(acc3);
        Savings acc4 = new Savings(new Profile("Ben", "Williams"), 10.02, new Date(3, 31, 2014), false);
        db.add(acc4);
        Checking acc5 = new Checking(new Profile("Jane", "Doe"), 67.89, new Date(7, 31, 2013), true);
        db.add(acc5);
        Checking acc6 = new Checking(new Profile("Brian", "Johnson"), 25000.39, new Date(3, 27, 2013), true);
        db.add(acc6);
        //System.out.println(db.printByDateOpen());
        //db.printByLastName();
        //db.remove(acc2);
        // db.deposit(acc5, 10000);
        // db.withdrawal(acc6, 25000.30);
        // db.printByDateOpen();
        //db.printFeesAndInterest();
        //System.out.println(db.printFeesAndInterest());
        System.out.println(db.printAccounts());
    }
}