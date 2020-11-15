package sample;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Order implements Customizable
{
	public static int lineNumber; //reset for each new order;
	private ArrayList<OrderLine> orderlines;
	
	public Order()
	{
		orderlines = new ArrayList<OrderLine>();
	}
	public boolean add(Object obj)
	{
		return orderlines.add((OrderLine)obj);
	}
	public boolean remove(Object obj)
	{
		return orderlines.remove((OrderLine)obj);
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

	public ArrayList<OrderLine> getOrderlines() {
		return orderlines;
	}

	public static void main(String[] args)
	{
		Order order1 = new Order();
		Chicken chicken = new Chicken();
		OrderLine order = new OrderLine(0, chicken, chicken.price());
		order1.add(order);
		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order2 = new OrderLine(0, chicken1, chicken1.price());
		order1.add(order2);
		System.out.println(order1.toString());
	}
}
