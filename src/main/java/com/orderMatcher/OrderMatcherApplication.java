package com.orderMatcher;

import com.orderMatcher.serviceImpl.BuyStockOrder;
import com.orderMatcher.serviceImpl.PrintStockOrder;
import com.orderMatcher.serviceImpl.SellStockOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.orderMatcher.service.Context;

import java.util.Scanner;

@Slf4j
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
      try {
        String input = scanner.next();
        switch (input) {
          case "BUY":
            new Context(new BuyStockOrder()).executeStrategy(scanner.next());
            break;
          case "SELL":
            new Context(new SellStockOrder()).executeStrategy(scanner.next());
          case "PRINT":
            new Context(new PrintStockOrder()).executeStrategy(input);
            break;
          default:
            System.out.println(Constants.INVALID_INPUT_COMMAND_OR_END);
            System.exit(1);
        }
      } catch (IllegalArgumentException ex) {
        log.info("tradingApplication:" + ex.getMessage());
      } catch (UnsupportedOperationException e) {
        log.info("Sorry, this command is not supported yet: " + e.getMessage());
      }
    }
  }
}
