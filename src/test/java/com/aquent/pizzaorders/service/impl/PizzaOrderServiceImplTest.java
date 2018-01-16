package com.aquent.pizzaorders.service.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.aquent.pizzaorders.entity.PizzaOrder;
import com.aquent.pizzaorders.repository.PizzaOrderRepository;

public class PizzaOrderServiceImplTest {
	
	
	PizzaOrderRepository repository = mock(PizzaOrderRepository.class);
	
	PizzaOrderServiceImpl service = new PizzaOrderServiceImpl(repository);
	
	@Test
	public void testReadInputFromTextFile() {
		List<PizzaOrder> pizzaOrders = new ArrayList<PizzaOrder>();
		PizzaOrder pizzaOrder = new PizzaOrder();
		pizzaOrder.setOrder("Bread");
		pizzaOrder.setTime("1477405487");
		pizzaOrders.add(pizzaOrder);
		
		when(repository.readInputFromTextFile("")).thenReturn(pizzaOrders);
		
		String actual = service.readInputFromTextFile("").get(0).getTime();
		String expected = "Tue, 25 Oct 2016 09:24:47 CDT";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetReadableFormat() {
		long myTimeAsLong = 1506176687;
		String actual = service.getReadableFormat(myTimeAsLong);
		String expected = "Sat, 23 Sep 2017 09:24:47 CDT";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSortedList() {
		List<PizzaOrder> pizzaOrders = new ArrayList<PizzaOrder>();
		PizzaOrder pizzaOrder1 = new PizzaOrder();
		pizzaOrder1.setOrder("bread");
		pizzaOrder1.setTime("Sat, 17 Jan 1970 19:37:20 CST");
		pizzaOrders.add(pizzaOrder1);
		
		PizzaOrder pizzaOrder2 = new PizzaOrder();
		pizzaOrder2.setOrder("Pizza");
		pizzaOrder2.setTime("Sat, 17 Jan 1970 20:21:59 CST");
		pizzaOrders.add(pizzaOrder2);
		
		PizzaOrder pizzaOrder3 = new PizzaOrder();
		pizzaOrder3.setOrder("Meat");
		pizzaOrder3.setTime("Sun, 18 Jan 1970 04:22:56 CST");
		pizzaOrders.add(pizzaOrder3);
		
		List<PizzaOrder> actualList = service.getSortedList(pizzaOrders);
		
		List<PizzaOrder> expectedList = new ArrayList<PizzaOrder>();
		PizzaOrder pizzaOrderExpected1 = new PizzaOrder();
		pizzaOrderExpected1.setOrder("Meat");
		pizzaOrderExpected1.setTime("Sun, 18 Jan 1970 04:22:56 CST");
		expectedList.add(pizzaOrderExpected1);
		
		PizzaOrder pizzaOrderExpected2 = new PizzaOrder();
		pizzaOrderExpected2.setOrder("Pizza");
		pizzaOrderExpected2.setTime("Sat, 17 Jan 1970 20:21:59 CST");
		expectedList.add(pizzaOrderExpected2);
		
		PizzaOrder pizzaOrderExpected3 = new PizzaOrder();
		pizzaOrderExpected3.setOrder("bread");
		pizzaOrderExpected3.setTime("Sat, 17 Jan 1970 19:37:20 CST");
		expectedList.add(pizzaOrderExpected3);
		
		assertEquals(expectedList.get(0).getOrder(), actualList.get(0).getOrder());
	}
	
}
