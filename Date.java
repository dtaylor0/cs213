package sample;

/**
 Implement Date object and gives it their attributes
 @author Shyam Patel, Drew Taylor
 */
public class Date implements Comparable<Date>
{
	private int year;
	private int month;
	private int day;
	
	/**
	 Constructor the give the Date object the fields of month, day, and year
	 @param month of the date
	 @param day of the date
	 @param year of the date
	 */
	public Date(int month, int day, int year)
	{
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	/**
	 Compare two different dates and see which is older or if they are the same date
	 @Override compareTo from Comparable interface
	 @param date Date to compare to
	 @return 0: same date, 1: parameter is the "older date", -1: parameter is the "newer" date
	 */
	public int compareTo(Date date) 
	{
		if(this.year > date.getYear())
			return 1;
		if(this.year < date.getYear())
			return -1;
		if(this.year == date.getYear())
		{
			if(this.month > date.getMonth())//if year is the same check the month
				return 1;
			if(this.month < date.getMonth())
				return -1;
			if(this.month == date.getMonth())//if month is the same check the day
			{
				if(this.day > date.getDay())
					return 1;
				if(this.day < date.getDay())
					return -1;
			}
		}
		return 0;//everything is equal
	}
	/**
	 toString method to return the fields of the Date object
	 @Override toString from Object class
	 @return String in the format mm/dd/yyyy
	 */
	public String toString() 
	{ 
		return month + "/" + day + "/" + year;
	}
	/**
	 Checks if a date is possible to occur
	 @return true if the date is valid and false if the date is not possible
	 */
	public boolean isValid() 
	{
		if(month <= 0 || day <= 0 || year <= 0 || month > 12)//12 months only and can't have negative values
			return false;
		if(month == Month.JAN || month == Month.MAR || month == Month.MAY || month == Month.JUL || month == Month.AUG || month == Month.OCT || month == Month.DEC)
		{
			if(day > Month.DAYS_ODD)
				return false;
		}
		if(month == Month.APR || month == Month.JUN || month == Month.SEP || month == Month.NOV)
		{
			if(day > Month.DAYS_EVEN)
				return false;
		}
		if(month == Month.FEB)
		{
			if(year % Month.CENTENNIAL == 0 && year % Month.QUATERCENTENNIAL != 0 && day > Month.DAYS_FEB)// years divisible by 100 don't have leap years
				return false;
			if(year % Month.QUATERCENTENNIAL == 0 && day > Month.DAYS_FEB + 1)//but years divisible by 400 have leap years
				return false;
			if(year % Month.QUADRENNIAL != 0 && day > Month.DAYS_FEB)//divisible by 4 so it has a leap year
				return false;
		}
		return true;
	}
	
	/**
	 Getter method to access the year field
	 @return year of the Date object
	 */
	public int getYear()
	{
		return year;
	}
	
	/**
	 Getter method to access the month field
	 @return month of the Date object
	 */
	public int getMonth()
	{
		return month;
	}
	/**
	 Getter method to access the day field
	 @return day of the Date object
	 */
	public int getDay()
	{
		return day;
	}
	
	/**
	 Testbed main to see if Date class methods work
	 @param args to run the class
	 */
	public static void main(String[] args)
	{
		Date date1 = new Date(0, 1, 2010);
		Date date2 = new Date(2, 29, 2296);
		Date date3 = new Date(2, 29, 2012);
		System.out.println(date2.compareTo(date3));
		System.out.println(date1.isValid());
		System.out.println(date3.toString());
	}
}
