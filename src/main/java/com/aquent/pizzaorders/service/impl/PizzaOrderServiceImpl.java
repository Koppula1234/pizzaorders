package com.aquent.pizzaorders.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aquent.pizzaorders.entity.PizzaOrder;
import com.aquent.pizzaorders.repository.PizzaOrderRepository;
import com.aquent.pizzaorders.service.PizzaOrderService;

@Component
public class PizzaOrderServiceImpl implements PizzaOrderService {

	private PizzaOrderRepository repository;
	
	public PizzaOrderServiceImpl(PizzaOrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<PizzaOrder> readInputFromTextFile(String inputFile) {
		List<PizzaOrder> pizzaOrders = repository.readInputFromTextFile(inputFile);
		pizzaOrders.forEach(pizzaOrder -> pizzaOrder.setTime(getReadableFormat(Long.parseLong(pizzaOrder.getTime()))));
		return pizzaOrders;
	}

	@Override
	public void writeResultsToOutputFile(String outputFile, List<PizzaOrder> pizzaOrders) {
		List<PizzaOrder> sortedPizzaOrders = getSortedList(pizzaOrders);
		repository.writeResultsToOutputFile(outputFile, sortedPizzaOrders);
	}

	public String getReadableFormat(long myTimeAsLong) {
		SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
		return sdf.format(new Date(myTimeAsLong*1000));
	}

	public List<PizzaOrder> getSortedList(List<PizzaOrder> pizzaOrders) {
		List<PizzaOrder> list = pizzaOrders;
		list.sort((PizzaOrder po1, PizzaOrder po2) -> {
			int v = po1.getOrder().compareTo(po2.getOrder());
			if(v == 0) {
				SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
				try {
					Date dateOfPo1 = formatter.parse(po1.getTime());
					Date dateOfPo2 = formatter.parse(po2.getTime());
					return dateOfPo1.compareTo(dateOfPo2);
				} catch(ParseException ex) {
					ex.printStackTrace();
				}
			}
			return v;
		});
		return list;
	}

}
