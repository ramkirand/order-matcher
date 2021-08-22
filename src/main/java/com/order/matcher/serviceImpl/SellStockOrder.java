package com.order.matcher.serviceImpl;

import com.order.matcher.Constants;
import com.order.matcher.model.StockOrder;
import com.order.matcher.service.TradingStrategy;
import com.order.matcher.util.OrderMatcherUtil;
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
              .stockInstructionNUmber(OrderMatcherUtil.instructionNum.get())
              .build();
      while (!OrderMatcherUtil.buyStockOrderMaxHeap.isEmpty()
          && Objects.requireNonNull(OrderMatcherUtil.buyStockOrderMaxHeap.peek()).getPrice()
              >= sellOrderPrice
          && sellOrder.getVolume() > 0) {
        StockOrder buyOrder = OrderMatcherUtil.buyStockOrderMaxHeap.remove();
        // Trading logic
        int currentSale = Math.min(buyOrder.getVolume(), sellOrder.getVolume());
        System.out.println(
            Constants.TRADE + getSpace() + currentSale + Constants.AT + buyOrder.getPrice());
        buyOrder.setVolume(buyOrder.getVolume() - currentSale);
        sellOrder.setVolume(sellOrder.getVolume() - currentSale);
        if (buyOrder.getVolume() > 0) OrderMatcherUtil.buyStockOrderMaxHeap.add(buyOrder);
      }
      if (sellOrder.getVolume() > 0) {
        OrderMatcherUtil.sellStockOrderMinHeap.add(sellOrder);
      }
    } catch (Exception ex) {
      log.info("In Sell stock:" + ex.getMessage());
    }
  }

  private void incrementInstruction() {
    OrderMatcherUtil.instructionNum.incrementAndGet();
  }

  private String getSpace() {
    return Constants.BLANK_SPACE;
  }
}
