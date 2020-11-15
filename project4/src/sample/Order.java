package sample;

import java.util.ArrayList;

public class Order implements Customizable
{
	public static int lineNumber; //reset for each new order;
	private ArrayList<OrderLine> orderlines;
	
	public Order()
	{
		Order.lineNumber = 0;
		orderlines = new ArrayList<OrderLine>();
	}
	public boolean add(Object obj)
	{
		if(orderlines.add((OrderLine)obj))
		{
			((OrderLine) obj).setLineNumber(orderlines.size());
			return true;
		}
		return false;
		
	}
	public boolean remove(Object obj)
	{
		return orderlines.remove((OrderLine)obj);
	}

	public boolean remove(int i) {
		Object o = orderlines.remove(i);
		if (o == null) {
			return false;
		}
		else {
			for (int index = 0; index < orderlines.size(); index++) {
				orderlines.get(index).setLineNumber(index + 1);
			}
			return true;
		}
	}

	public Sandwich getSandwich(int i) {
		return orderlines.get(i).getSandwich();
	}

	public String toString()
	{
		String order = "";
		for(int i = 0; i < orderlines.size(); i++)
		{
		    order += orderlines.get(i).toString();
		}
		return order;
	}

	public double totalPrice() {
		double total = 0;
		for (int i = 0; i < orderlines.size(); i++) {
			total += orderlines.get(i).getSandwich().price();
		}
		return total;
	}

	public static void main(String[] args)
	{
		Order order1 = new Order();
		Chicken chicken = new Chicken();
		OrderLine order = new OrderLine(0, chicken, chicken.price());
		Beef beef = new Beef();
		OrderLine order6 = new OrderLine(0, beef, beef.price());
		order1.add(order6);
		order1.add(order);
		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order2 = new OrderLine(0, chicken1, chicken1.price());
		order1.add(order2);
		Chicken chicken2 = new Chicken();
		chicken2.add(new Extra("Onion"));
		OrderLine order5 = new OrderLine(0, chicken2, chicken2.price());
		order1.add(order5);
		System.out.println(order1.toString());
		//System.out.println(order3.toString());
		//System.out.println(order4.toString());
	}
}
