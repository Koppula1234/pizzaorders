package com.aquent.pizzaorders.service;

import java.util.List;

import com.aquent.pizzaorders.entity.PizzaOrder;

public interface PizzaOrderService {
	
	public List<PizzaOrder> readInputFromTextFile(String inputFile);
	
	public void writeResultsToOutputFile(String outputFile, List<PizzaOrder> pizzaOrders);

}
