package com.aquent.pizzaorders.repository;

import java.util.List;

import com.aquent.pizzaorders.entity.PizzaOrder;

public interface PizzaOrderRepository {

	public List<PizzaOrder> readInputFromTextFile(String inputFile);
	
	public void writeResultsToOutputFile(String outputFile, List<PizzaOrder> sortedPizzaOrders);
}
