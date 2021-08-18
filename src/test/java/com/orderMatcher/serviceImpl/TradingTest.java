package com.orderMatcher.serviceImpl;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import com.orderMatcher.service.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradingTest {

  private List<String> commandList;

  @BeforeEach
  public void init() {
    commandList = new ArrayList<>();
  }

  @Test
  @Order(1)
  public void shouldVerifyScenario1() {
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "100@10");
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "100@15");
    commandList.add(TransactionEnum.BUY + Constants.SPACE + "120@17");
    commandList.add(TransactionEnum.PRINT.getCommand());
    commandList.forEach(
        cmd -> {
          if (cmd.startsWith("BUY")) {
            new Context(new BuyStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("SELL")) {
            new Context(new SellStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("PRINT")) {
            new Context(new PrintStockOrder()).executeStrategy(cmd.trim());
          }
        });
    commandList.clear();
  }

  @Test
  @Order(2)
  public void shouldVerifyScenario2() {
    commandList.add(TransactionEnum.BUY + Constants.SPACE + "100@50");
    commandList.add(TransactionEnum.BUY + Constants.SPACE + "1000@25");
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "500@35");
    commandList.add(TransactionEnum.PRINT.getCommand());
    commandList.forEach(
        cmd -> {
          if (cmd.startsWith("BUY")) {
            new Context(new BuyStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("SELL")) {
            new Context(new SellStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("PRINT")) {
            new Context(new PrintStockOrder()).executeStrategy(cmd.trim());
          }
        });
    commandList.clear();
  }

  @Test
  @Order(3)
  public void shouldVerifyScenario3() {

    commandList.add(TransactionEnum.SELL + Constants.SPACE + "100@55");
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "500@67");
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "200@88");
    commandList.add(TransactionEnum.BUY + Constants.SPACE + "1000@44");
    commandList.add(TransactionEnum.BUY + Constants.SPACE + "3000@33");
    commandList.add(TransactionEnum.PRINT.getCommand());
    commandList.forEach(
        cmd -> {
          if (cmd.startsWith("BUY")) {
            new Context(new BuyStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("SELL")) {
            new Context(new SellStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("PRINT")) {
            new Context(new PrintStockOrder()).executeStrategy(cmd.trim());
          }
        });
    commandList.clear();
  }

  @Test
  @Order(4)
  public void shouldVerifyScenario4() {

    commandList.add(TransactionEnum.BUY + Constants.SPACE + "100@10");
    commandList.add(TransactionEnum.BUY + Constants.SPACE + "100@10");
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "10@10");
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "10@10");
    commandList.add(TransactionEnum.PRINT.getCommand());
    commandList.add(TransactionEnum.SELL + Constants.SPACE + "10@10");
    commandList.add(TransactionEnum.PRINT.getCommand());
    commandList.forEach(
        cmd -> {
          if (cmd.startsWith("BUY")) {
            new Context(new BuyStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("SELL")) {
            new Context(new SellStockOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
          } else if (cmd.startsWith("PRINT")) {
            new Context(new PrintStockOrder()).executeStrategy(cmd.trim());
          }
        });
    commandList.clear();
  }
}
