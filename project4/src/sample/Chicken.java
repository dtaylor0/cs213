package sample;

public class Chicken extends Sandwich
{	
	
	public Chicken()
	{
		super();
		add(new Extra("Fried Chicken"));
		add(new Extra("Spicy Sauce"));
		add(new Extra("Pickles"));
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
		
		return 8.99 + (extras.size() - 3) * PER_EXTRA;
	}
	
	public String toString() 
	{ 
		return "Chicken Sandwich, " + super.toString();
	}
	
	public static void main(String[] args)
	{
		Chicken chicken = new Chicken();
		Extra extra = new Extra("Onion");
		chicken.remove(extra);
		chicken.add(extra);
		chicken.remove(extra);
		chicken.add(new Extra("Tomato"));
		chicken.add(new Extra("Lettuce"));
		chicken.add(new Extra("Spinach"));
		chicken.add(new Extra("Mayo"));
		chicken.add(new Extra("Ketchup"));
		chicken.add(new Extra("Mustard"));
		chicken.add(new Extra("Bacon"));
		
		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		System.out.println(chicken.toString() + " " + chicken.price());
		System.out.println(chicken1.toString() + " " + chicken1.price());
	}
}
