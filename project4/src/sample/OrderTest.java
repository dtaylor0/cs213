package sample;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.Test;
import org.junit.jupiter.api.Test;

class OrderTest 
{	
	@Test
    void testAdd() 
    {
		//example order
		Order order = new Order();
		
		//add chicken sandwich
		Chicken chicken = new Chicken();
		OrderLine order1 = new OrderLine(0, chicken, chicken.price());
		assertEquals(order.add(order1), true);
		
		//add beef sandwich
		Beef beef = new Beef();
		OrderLine order2 = new OrderLine(0, beef, beef.price());
		assertEquals(order.add(order2), true);
		
		//add chicken sandwich with toppings
		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order3 = new OrderLine(0, chicken1, chicken1.price());
		assertEquals(order.add(order3), true);
		
		//add a chicken sandwich that looks like a duplicate
		Chicken chicken2 = new Chicken();
		OrderLine order4 = new OrderLine(0, chicken2, chicken2.price());
		assertEquals(order.add(order4), true);
			
		//add a fish sandwich
		Fish fish = new Fish();
		fish.add(new Extra("Onion"));
		OrderLine order5 = new OrderLine(0, fish, fish.price());
		assertEquals(order.add(order5), true);
				
    }
	
	@Test
    void testRemove() 
    {
		Order order = new Order();

		Chicken chicken = new Chicken();
		OrderLine order1 = new OrderLine(0, chicken, chicken.price());

		Beef beef = new Beef();
		OrderLine order2 = new OrderLine(0, beef, beef.price());

		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order3 = new OrderLine(0, chicken1, chicken1.price());

		Fish fish = new Fish();
		fish.add(new Extra("Onion"));
		OrderLine order4 = new OrderLine(0, fish, fish.price());

		order.add(order1);
		order.add(order2);
		order.add(order3);

		assertEquals(order.remove(order3), true);
		assertEquals(order.remove(order4), false);
    }	

    @Test
	void testRemoveAt()
	{
		Order order = new Order();

		Chicken chicken = new Chicken();
		OrderLine order1 = new OrderLine(0, chicken, chicken.price());

		Beef beef = new Beef();
		OrderLine order2 = new OrderLine(0, beef, beef.price());

		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order3 = new OrderLine(0, chicken1, chicken1.price());

		Fish fish = new Fish();
		fish.add(new Extra("Onion"));
		OrderLine order4 = new OrderLine(0, fish, fish.price());

		order.add(order1);
		order.add(order2);
		order.add(order3);
		order.add(order4);

		assertEquals(order.removeAt(4), false);
		assertEquals(order.removeAt(3), true);
		assertEquals(order.removeAt(-1), false);
	}

	@Test
    void testGetSandwich() 
    {
		//example order
		Order order = new Order();
		
		Chicken chicken = new Chicken();
		OrderLine order1 = new OrderLine(0, chicken, chicken.price());
		
		Beef beef = new Beef();
		OrderLine order2 = new OrderLine(0, beef, beef.price());
		
		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order3 = new OrderLine(0, chicken1, chicken1.price());
		
		Fish fish = new Fish();
		fish.add(new Extra("Onion"));
		OrderLine order4 = new OrderLine(0, fish, fish.price());
		
		order.add(order1);
		order.add(order2);
		order.add(order3);
		order.add(order4);
		//get Chicken
		assertEquals(order.getSandwich(0), chicken);
		//get Beef
		assertEquals(order.getSandwich(1), beef);
		//get second Chicken
		assertEquals(order.getSandwich(2), chicken1);
		//get Fish
		assertEquals(order.getSandwich(3), fish);
		//index doesn't have a sandwich
		assertEquals(order.getSandwich(4), null);
		//index is negative
		assertEquals(order.getSandwich(-1), null);
    }
	
	@Test
    void testToString() 
    {
		Order order = new Order();
		
		//test toString without items
		assertEquals(order.toString(), "");
		Chicken chicken = new Chicken();
		OrderLine order1 = new OrderLine(0, chicken, chicken.price());
		
		Beef beef = new Beef();
		OrderLine order2 = new OrderLine(0, beef, beef.price());
		
		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order3 = new OrderLine(0, chicken1, chicken1.price());
		
		Fish fish = new Fish();
		fish.add(new Extra("Onion"));
		OrderLine order4 = new OrderLine(0, fish, fish.price());
		
		order.add(order1);
		order.add(order2);
		order.add(order3);
		order.add(order4);
		
		//test toString with items
		assertEquals(order.toString(), "1 Chicken Sandwich, Fried Chicken, Spicy Sauce, Pickles, Price $8.99\n"
				+ "2 Beef Sandwich, Roast Beef, Provolone Cheese, Mustard, Price $10.99\n"
				+ "3 Chicken Sandwich, Fried Chicken, Spicy Sauce, Pickles, Jalapenos, Price $10.98\n"
				+ "4 Fish Sandwich, Grilled Snapper, Cilantro, Lime, Onion, Price $14.98\n");
    }
	
	@Test
    void testTotalPrice() 
    {
		Order order = new Order();
		
		//test with no items
		assertEquals(order.totalPrice(), 0.0);
		
		Chicken chicken = new Chicken();
		OrderLine order1 = new OrderLine(0, chicken, chicken.price());
		
		Beef beef = new Beef();
		OrderLine order2 = new OrderLine(0, beef, beef.price());
		
		Chicken chicken1 = new Chicken();
		chicken1.add(new Extra("Jalapenos"));
		OrderLine order3 = new OrderLine(0, chicken1, chicken1.price());
		
		Fish fish = new Fish();
		fish.add(new Extra("Onion"));
		OrderLine order4 = new OrderLine(0, fish, fish.price());
		
		order.add(order1);
		order.add(order2);
		order.add(order3);
		order.add(order4);
		
		//test with items
		assertEquals(order.totalPrice(), 45.94);
    }
}
