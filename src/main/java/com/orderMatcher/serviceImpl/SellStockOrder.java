package com.orderMatcher.serviceImpl;

import com.orderMatcher.Constants;
import com.orderMatcher.model.StockOrder;
import com.orderMatcher.service.TradingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class SellStockOrder implements TradingStrategy {

  @Override
  public void execute(String inputCommand) {
    try {
      incrementInstruction();
      int sellOrderPrice = Integer.parseInt(inputCommand.split(Constants.AT)[1]);
      int sellOrderVolume = Integer.parseInt(inputCommand.split(Constants.AT)[0]);
      StockOrder sellOrder =
          StockOrder.builder()
              .price(sellOrderPrice)
              .volume(sellOrderVolume)
              .instructionNum(TradingStrategy.instructionNum.get())
              .build();
      while (!buyStockOrderMaxHeap.isEmpty()
          && Objects.requireNonNull(buyStockOrderMaxHeap.peek()).getPrice() >= sellOrderPrice
          && sellOrder.getVolume() > 0) {
        StockOrder buyOrder = buyStockOrderMaxHeap.remove();
        // Trading logic
        int currentSale = Math.min(buyOrder.getVolume(), sellOrder.getVolume());
        System.out.println(
            Constants.TRADE + getSpace() + currentSale + Constants.AT + buyOrder.getPrice());
        buyOrder.setVolume(buyOrder.getVolume() - currentSale);
        sellOrder.setVolume(sellOrder.getVolume() - currentSale);
        if (buyOrder.getVolume() > 0) buyStockOrderMaxHeap.add(buyOrder);
      }
      if (sellOrder.getVolume() > 0) {
        sellStockOrderMinHeap.add(sellOrder);
      }
    } catch (Exception ex) {
      log.info("In Sell stock:" + ex.getMessage());
    }
  }

  private void incrementInstruction() {
    TradingStrategy.instructionNum.incrementAndGet();
  }

  private String getSpace() {
    return Constants.SPACE;
  }
}
