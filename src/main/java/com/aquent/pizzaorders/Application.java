package com.aquent.pizzaorders;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aquent.pizzaorders.entity.PizzaOrder;
import com.aquent.pizzaorders.service.PizzaOrderService;
import com.aquent.pizzaorders.service.impl.PizzaOrderServiceImpl;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);	
		
		PizzaOrderService service = context.getBean(PizzaOrderServiceImpl.class);
		List<PizzaOrder> pizzaOrders = service.readInputFromTextFile(args[0]);
		service.writeResultsToOutputFile(args[1], pizzaOrders);
		
		context.close();
	}
}
