package sample;

public class OrderLine 
{
	private int lineNumber; //a serial number created when a sandwich is added to the order
	private Sandwich sandwich;
	private double price;
	
	public OrderLine(int lineNumber, Sandwich sandwich, double price)
	{
		this.lineNumber = lineNumber;
		this.sandwich = sandwich;
		this.price = price;
	}
	
	public String toString()
	{
		return lineNumber + " " + sandwich.toString() + String.format(", Price $%.2f", price) + "\n";
	}
	
	public int getLineNumber()
	{
		return this.lineNumber;
	}
	
	public void setLineNumber(int lineNumber)
	{
		this.lineNumber = lineNumber;
	}

	public Sandwich getSandwich() {
		return sandwich;
	}
}
