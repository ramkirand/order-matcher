package com.order.matcher.serviceImpl;

import com.order.matcher.Constants;
import com.order.matcher.model.StockOrder;
import com.order.matcher.service.TradingStrategy;
import com.order.matcher.util.OrderMatcherUtil;
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
              .stockInstructionNUmber(OrderMatcherUtil.instructionNum.get())
              .build();
      while (!OrderMatcherUtil.sellStockOrderMinHeap.isEmpty()
          && Objects.requireNonNull(OrderMatcherUtil.sellStockOrderMinHeap.peek()).getPrice()
              <= buyOrderPrice
          && buyOrder.getVolume() > 0) {
        StockOrder sellOrder = OrderMatcherUtil.sellStockOrderMinHeap.remove();
        // Trading logic
        int currentSale = Math.min(buyOrder.getVolume(), sellOrder.getVolume());
        System.out.println(
            Constants.TRADE
                + Constants.BLANK_SPACE
                + currentSale
                + Constants.AT
                + sellOrder.getPrice());
        buyOrder.setVolume(buyOrder.getVolume() - currentSale);
        sellOrder.setVolume(sellOrder.getVolume() - currentSale);
        if (sellOrder.getVolume() > 0) OrderMatcherUtil.sellStockOrderMinHeap.add(sellOrder);
      }
      if (buyOrder.getVolume() > 0) {
        OrderMatcherUtil.buyStockOrderMaxHeap.add(buyOrder);
      }
    } catch (Exception ex) {
      log.info("In BuyStock: " + ex.getMessage());
    }
  }

  private void incrementInstruction() {
    OrderMatcherUtil.instructionNum.getAndIncrement();
  }
}
