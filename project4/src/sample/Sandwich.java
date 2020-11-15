package sample;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Sandwich implements Customizable
{
	static final int MAX_EXTRAS = 6;
	static final double PER_EXTRA = 1.99;
	protected ArrayList<Extra> extras;

	public Sandwich() 
	{
		extras = new ArrayList<Extra>();
	}

	public abstract double price();
	
	public String toString() 
	{ 
		return extras.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
	}
}
