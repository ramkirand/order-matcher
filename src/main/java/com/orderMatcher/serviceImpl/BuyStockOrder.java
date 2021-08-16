package com.orderMatcher.serviceImpl;

import com.orderMatcher.Constants;
import com.orderMatcher.model.StockOrder;
import com.orderMatcher.service.TradingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class BuyStockOrder implements TradingStrategy {
  @Override
  public void execute(String inputCommand) {
    try {
      incrementInstruction();
      int buyOrderPrice = Integer.parseInt(inputCommand.split(Constants.AT)[1]);
      int buyOrderVolume = Integer.parseInt(inputCommand.split(Constants.AT)[0]);
      StockOrder buyOrder =
          StockOrder.builder()
              .price(buyOrderPrice)
              .volume(buyOrderVolume)
              .instructionNum(TradingStrategy.instructionNum.get())
              .build();
      while (!sellStockOrderMinHeap.isEmpty()
          && Objects.requireNonNull(sellStockOrderMinHeap.peek()).getPrice() <= buyOrderPrice
          && buyOrder.getVolume() > 0) {
        StockOrder sellOrder = sellStockOrderMinHeap.remove();
        // Trading logic
        int currentSale = Math.min(buyOrder.getVolume(), sellOrder.getVolume());
        System.out.println(
            Constants.TRADE + Constants.SPACE + currentSale + Constants.AT + sellOrder.getPrice());
        buyOrder.setVolume(buyOrder.getVolume() - currentSale);
        sellOrder.setVolume(sellOrder.getVolume() - currentSale);
        if (sellOrder.getVolume() > 0) sellStockOrderMinHeap.add(sellOrder);
      }
      if (buyOrder.getVolume() > 0) {
        buyStockOrderMaxHeap.add(buyOrder);
      }
    } catch (Exception ex) {
      log.info("In BuyStock: " + ex.getMessage());
    }
  }

  private void incrementInstruction() {
    TradingStrategy.instructionNum.incrementAndGet();
  }
}
