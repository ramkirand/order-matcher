package com.orderMatcher.serviceImpl;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import com.orderMatcher.model.StockOrder;
import com.orderMatcher.service.TradingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class BuyOrder implements TradingStrategy {
    @Override public void execute(String inputCommand) {
        try {
            StockOrder buyOrder = null;
            int buyOrderPrice = Integer.parseInt(inputCommand.split(Constants.AT)[1]);
            int buyOrderVolume = Integer.parseInt(inputCommand.split(Constants.AT)[0]);
            if (sellOrderBookMinHeap.isEmpty()) {
                buyOrder = StockOrder.builder()
                    .price(buyOrderPrice)
                    .volume(buyOrderVolume)
                    .transactionType(TransactionEnum.BUY.getCommand())
                    .build();
                buyOrderBookMaxHeap.add(buyOrder);
            } else {
                while (!sellOrderBookMinHeap.isEmpty()
                    && Objects.requireNonNull(
                        sellOrderBookMinHeap.peek()).getPrice() >= buyOrderPrice) {
                    sellOrderBookMinHeap.remove();
                }
            }
            if (buyOrder != null && buyOrder.getVolume() > 0) {
                allBuyOrders.add(buyOrder);
                allBuyOrders.sort(new OrderComparator());
            }
        } catch (Exception ex) {
            log.info("In BuyStock: " + ex.getLocalizedMessage());
        }

    }
}
