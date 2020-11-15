package sample;

import java.util.ArrayList;

public class Fish extends Sandwich 
{
	public Fish()
	{
		super();
		add(new Extra("Grilled Snapper"));
		add(new Extra("Cilantro"));
		add(new Extra("Lime"));
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
		return 12.99 + (extras.size() - 3) * 1.99;
	}
	
	public String toString() 
	{ 
		return "Fish Sandwich, " + super.toString();
	}
}
