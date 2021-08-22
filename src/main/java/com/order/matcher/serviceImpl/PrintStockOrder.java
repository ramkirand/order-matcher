package com.order.matcher.serviceImpl;

import com.order.matcher.Constants;
import com.order.matcher.enums.TransactionEnum;
import com.order.matcher.service.TradingStrategy;
import com.order.matcher.util.OrderMatcherUtil;

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
    OrderMatcherUtil.sellStockOrderMinHeap.forEach(
        sellStockOrder -> {
          if (sellStockOrder.getVolume() > 0)
            System.out.println(
                TransactionEnum.SELL.getCommand()
                    + Constants.BLANK_SPACE
                    + sellStockOrder.getVolume()
                    + Constants.AT
                    + sellStockOrder.getPrice());
        });
    OrderMatcherUtil.buyStockOrderMaxHeap.forEach(
        buyStockOrder -> {
          if (buyStockOrder.getVolume() > 0)
            System.out.println(
                TransactionEnum.BUY.getCommand()
                    + Constants.BLANK_SPACE
                    + buyStockOrder.getVolume()
                    + Constants.AT
                    + buyStockOrder.getPrice());
        });
  }
}
