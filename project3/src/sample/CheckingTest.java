package sample;

/**
 JUnit test for Checking
 @author Shyam Patel, Drew Taylor
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest
{

    /**
     Test if monthlyInterest works
     */
    @Test
    void testMonthlyInterest() 
    {
        double checkingAnnualRate = 0.0005;
        assertEquals(new Checking(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), true).monthlyInterest(), 100 * checkingAnnualRate / 12);
    }
    
    /**
    Test if monthlyFee works
    */
    @Test
    void testMonthlyFee()
    {
        double fee = 25;
        //direct deposit so fee should be 0
        assertEquals(new Checking(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), true).monthlyFee(), 0);
        //not direct deposit, below min waive balance
        assertEquals(new Checking(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), false).monthlyFee(), fee);
        //not direct deposit, but above min balance $1500
        assertEquals(new Checking(new Profile("Drew", "Taylor"), 1700, new Date(12, 12, 2012), false).monthlyFee(), 0);
    }
    
    /**
    Test if getDirectDeposit works
    */
    @Test
    void testGetDirectDeposit() 
    {
        //direct deposit (true)
        assertEquals(new Checking(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), true).getDirectDeposit(), true);
        //not direct deposit (false)
        assertEquals(new Checking(new Profile("Drew", "Taylor"), 100, new Date(12, 12, 2012), false).getDirectDeposit(), false);

    }
}