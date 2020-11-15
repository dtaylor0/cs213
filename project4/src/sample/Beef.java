package sample;

import java.util.ArrayList;

public class Beef extends Sandwich
{
	public Beef()
	{
		super();
		add(new Extra("Roast Beef"));
		add(new Extra("Provolone Cheese"));
		add(new Extra("Mustard"));
	}
	public boolean add(Object obj)
	{
		if(extras.size() <= MAX_EXTRAS + 2)
		{
			extras.add((Extra)obj);
			return true;
		}
		return false;
	}
	
	public boolean remove(Object obj)
	{
		if(extras.size() > 0)
		{
			extras.remove((Extra) obj);
			return true;
		}
		return false;
	}
	
	public double price()
	{
		return 10.99 + (extras.size() - 3) * 1.99;
	}
	
	public String toString() 
	{ 
		return "Beef Sandwich, " + super.toString();
	}
}
