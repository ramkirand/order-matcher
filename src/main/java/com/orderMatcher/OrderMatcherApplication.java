package com.orderMatcher;

import com.orderMatcher.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j @SpringBootApplication
public class OrderMatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMatcherApplication.class, args);
        Context context = new Context(new BuyStock());
        context.executeStrategy(TransactionEnum.BUY + Constants.SPACE + "100@50");
        context.executeStrategy(TransactionEnum.BUY + Constants.SPACE + "1000@25");
        context = new Context(new SellStock());
        context.executeStrategy(TransactionEnum.SELL + Constants.SPACE + "500@35");
        PrintStocks printStocks = new PrintStocks(context.getTradingStrategy());
        printStocks.displayResults();
    }

}
