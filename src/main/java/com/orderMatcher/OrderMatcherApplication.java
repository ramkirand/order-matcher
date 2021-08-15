package com.orderMatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.orderMatcher.service.BuyStock;
import com.orderMatcher.service.Context;
import com.orderMatcher.service.PrintStocks;
import com.orderMatcher.service.SellStock;

@SpringBootApplication
public class OrderMatcherApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderMatcherApplication.class, args);
		Context context = new Context(new BuyStock());
		context.executeStrategy(TransactionEnum.BUY + Constants.SPACE + "100@50");
		context.executeStrategy(TransactionEnum.BUY + Constants.SPACE + "1000@25");
		context = new Context(new SellStock());
		context.executeStrategy(TransactionEnum.SELL + Constants.SPACE + "500@35");
		PrintStocks printStocks = new PrintStocks();
		printStocks.displayResults();
	}

}
