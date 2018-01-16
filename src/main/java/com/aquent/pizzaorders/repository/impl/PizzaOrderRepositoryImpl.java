package com.aquent.pizzaorders.repository.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aquent.pizzaorders.entity.PizzaOrder;
import com.aquent.pizzaorders.repository.PizzaOrderRepository;

@Component
public class PizzaOrderRepositoryImpl implements PizzaOrderRepository {

	@Override
	public List<PizzaOrder> readInputFromTextFile(String inputFile) {
		List<PizzaOrder> pizzaOrders = new ArrayList<PizzaOrder>();
		try {
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				String[] sarr = line.split("\\s+");
				PizzaOrder pizzaOrder = new PizzaOrder();
				pizzaOrder.setOrder(sarr[0]);
				pizzaOrder.setTime(sarr[1]);
				pizzaOrders.add(pizzaOrder);
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file");
		} catch (IOException ex) {
			System.out.println("Error reading file");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return pizzaOrders;
	}

	@Override
	public void writeResultsToOutputFile(String outputFile, List<PizzaOrder> sortedPizzaOrders) {
		try {
			PrintWriter outputStream = new PrintWriter(outputFile);
			outputStream.println("Order" + String.format("%22s", "Time"));
			for (PizzaOrder pizzaOrder : sortedPizzaOrders) {
				int spaces = 40 - pizzaOrder.getOrder().length();
				outputStream.println(pizzaOrder.getOrder() + String.format("%" + spaces + "s", pizzaOrder.getTime()));
			}
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
