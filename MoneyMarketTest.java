package sample;

/**
JUnit test for Date
@author Shyam Patel, Drew Taylor
*/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyMarketTest
{

	/**
    Test if monthlyInterest works
    */
    @Test
    void testMonthlyInterest() 
    {
        double annualRate = 0.0065;
        assertEquals(new MoneyMarket(new Profile("Drew", "Taylor"), 1000, new Date(12, 12, 2012), 0).monthlyInterest(), 1000 * annualRate / 12);
    }

    /**
    Test if monthlyFee works
    */
    @Test
    void testMonthlyFee() 
    {
        double moneyMarketFee = 12;
        //below min balance, normal fee
        assertEquals(new MoneyMarket(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), 0).monthlyFee(), moneyMarketFee);
        //above min balance $2500 and <=6 withdrawals, fee waived
        assertEquals(new MoneyMarket(new Profile("Drew", "Taylor"), 15000, new Date(12, 12, 2012), 2).monthlyFee(), 0);
        //above min balance $2500 and >6 withdrawals, normal fee
        assertEquals(new MoneyMarket(new Profile("Drew", "Taylor"), 17000, new Date(12, 12, 2012), 8).monthlyFee(), moneyMarketFee);
    }

    /**
    Test if getWithdrawals works
    */
    @Test
    void testGetWithdrawals() 
    {
        assertEquals(new MoneyMarket(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), 3).getWithdrawals(), 3);
    }

    /**
    Test if incrementWithdrawals works
    */
    @Test
    void testIncrementWithdrawals() 
    {
        //change from 1 to 2 withdrawals
        MoneyMarket acc = new MoneyMarket(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), 1);
        acc.incrementWithdrawals();
        assertEquals(acc.getWithdrawals(), 2);
    }
}