package com.order.matcher.serviceImpl;

import com.order.matcher.Constants;
import com.order.matcher.enums.TransactionEnum;
import com.order.matcher.service.TradingStrategy;

public class PrintStockOrder implements TradingStrategy {

  @Override
  public void execute(String inputCommand) {
    try {
      if (inputCommand.startsWith(TransactionEnum.PRINT.getCommand())) {
        System.out.println("Display results:");
        displayResults();
        System.out.println();
      }
    } catch (Exception ignored) {
    }
  }

  private void displayResults() {
    TradingStrategy.sellStockOrderMinHeap.forEach(
        sellStockOrder -> {
          if (sellStockOrder.getVolume() > 0)
            System.out.println(
                TransactionEnum.SELL.getCommand()
                    + Constants.SPACE
                    + sellStockOrder.getVolume()
                    + Constants.AT
                    + sellStockOrder.getPrice());
        });
    TradingStrategy.buyStockOrderMaxHeap.forEach(
        buyStockOrder -> {
          if (buyStockOrder.getVolume() > 0)
            System.out.println(
                TransactionEnum.BUY.getCommand()
                    + Constants.SPACE
                    + buyStockOrder.getVolume()
                    + Constants.AT
                    + buyStockOrder.getPrice());
        });
  }
}
