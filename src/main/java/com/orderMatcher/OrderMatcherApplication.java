package com.orderMatcher;

import com.orderMatcher.serviceImpl.BuyOrder;
import com.orderMatcher.serviceImpl.PrintStockOrders;
import com.orderMatcher.serviceImpl.SellOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.orderMatcher.service.Context;

import java.util.Scanner;

@SpringBootApplication
public class OrderMatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMatcherApplication.class, args);
        tradingApplication();
    }

    private static void tradingApplication() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Constants.ENTER_INPUT_COMMAND);
            String input = scanner.next();
            switch (input) {
                case "BUY":
                    new Context(new BuyOrder()).executeStrategy(scanner.next());
                    break;
                case "SELL":
                    new Context(new SellOrder()).executeStrategy(scanner.next());
                case "PRINT":
                    new Context(new PrintStockOrders()).executeStrategy(input);
                    break;
                default:
                    System.out.println(Constants.INVALID_INPUT_COMMAND_OR_END);
                    System.exit(1);

            }
        }
    }
}
