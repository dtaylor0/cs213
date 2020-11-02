package sample;

/**
JUnit test for Date
@author Shyam Patel, Drew Taylor
*/
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest 
{
	/**
    Test if compareTo works
    */
    @Test
    void testCompareTo() 
    {
        //equivalent
        assertEquals((new Date(2, 2, 2002).compareTo(new Date(2, 2, 2002))), 0);
        //different day same month/year (left > right)
        assertEquals((new Date(2, 6, 2002).compareTo(new Date(2, 2, 2002))), 1);
        //different day/month same year (right > left)
        assertEquals((new Date(1, 6, 2002).compareTo(new Date(2, 2, 2002))), -1);
        //different year (left > right)
        assertEquals((new Date(1, 6, 2003).compareTo(new Date(2, 2, 2002))), 1);
    }
    /**
    Test if toString works
    */
    @Test
    void testToString()
    {
        //equivalent
        assertEquals((new Date(2, 2, 2002)).toString(), "2/2/2002");
        //different year
        assertNotEquals((new Date(2, 2, 2012)).toString(), "2/2/2002");
    }

    /**
    Test if isValid works
    */
    @Test
    void testIsValid() 
    {

        //31 days non leap year
        assertTrue((new Date(3, 31, 2003)).isValid());
        //31 days leap year
        assertTrue((new Date(1, 31, 2008)).isValid());
        //30 days non leap year
        assertTrue((new Date(4, 30, 2006)).isValid());
        assertFalse((new Date(4, 31, 2006)).isValid());
        //30 days leap year
        assertTrue((new Date(6, 30, 2004)).isValid());
        assertFalse((new Date(6, 31, 2004)).isValid());
        //28/29 days non leap year
        assertTrue((new Date(2, 28, 2003)).isValid());
        assertFalse((new Date(2, 29, 2003)).isValid());
        //28/29 days leap year
        assertTrue((new Date(2, 29, 2004)).isValid());
        //month < 1
        assertFalse((new Date(0, 20, 1900)).isValid());
        //month > 12
        assertFalse((new Date(13, 3, 2008)).isValid());
        //day < 1
        assertFalse((new Date(4, 0, 2002)).isValid());
        //year < 0
        assertFalse((new Date(4, 4, -2002)).isValid());


        //non leap year century
        assertTrue((new Date(2, 28, 1900)).isValid());
        assertFalse((new Date(2, 29, 1900)).isValid());

        //leap year century
        assertTrue((new Date(2, 29, 2000)).isValid());
    }

    /**
    Test if getYear works
    */
    @Test
    void testGetYear() 
    {
        assertEquals((new Date(2, 2, 2005)).getYear(), 2005);
        assertNotEquals((new Date(2, 2, 2004)).getYear(), 2005);
    }

    /**
    Test if getMonth works
    */
    @Test
    void testGetMonth() 
    {
        assertEquals((new Date(2, 2, 2005)).getMonth(), 2);
        assertNotEquals((new Date(5, 2, 2004)).getMonth(), 2);
    }

    /**
    Test if getDay works
    */
    @Test
    void testGetDay() 
    {
        assertEquals((new Date(2, 2, 2005)).getDay(), 2);
        assertNotEquals((new Date(2, 4, 2004)).getDay(), 2);
    }
}