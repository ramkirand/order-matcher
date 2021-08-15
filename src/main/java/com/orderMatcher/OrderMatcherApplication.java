package com.orderMatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.orderMatcher.service.BuyStock;
import com.orderMatcher.service.Context;
import com.orderMatcher.service.PrintStocks;
import com.orderMatcher.service.SellStock;

import java.util.Scanner;

@Slf4j @SpringBootApplication public class OrderMatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMatcherApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        System.out.println("example:" + "BUY 100@50," + "BUY 1000@25," + "SELL 500@35" + "exit to end the program");
        while (true) {
            System.out.println("Enter input command");
            String input = scanner.next();
            if (input.startsWith("BUY")) {
                new Context(new BuyStock()).executeStrategy(scanner.next());
            } else if (input.startsWith("SELL")) {
                new Context(new SellStock()).executeStrategy(scanner.next());
            } else if (input.startsWith("PRINT")) {
                new Context(new PrintStocks()).executeStrategy(input);
            } else {
                System.out.println("Invalid input command");
                System.exit(1);
            }
        }
    }
}
