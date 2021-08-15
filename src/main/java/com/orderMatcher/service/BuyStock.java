package com.orderMatcher.service;

import java.util.Objects;
import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import com.orderMatcher.model.Stock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyStock implements TradingStrategy {
    @Override public void execute(String inputCommand) {
        try {
            Stock buyOrder = null;
            int buyOrderPrice = Integer.parseInt(inputCommand.split(Constants.AT)[1]);
            int buyOrderVolume = Integer.parseInt(inputCommand.split(Constants.AT)[0]);
            if (sellOrderBookMinHeap.isEmpty()) {
                buyOrder = new Stock();
                buyOrder.setPrice(buyOrderPrice);
                buyOrder.setVolume(buyOrderVolume);
                buyOrder.setTransactionType(TransactionEnum.BUY.getStockMeta());
                buyOrderBookMaxHeap.add(buyOrder);
            } else {
                while (!sellOrderBookMinHeap.isEmpty()
                    && Objects.requireNonNull(sellOrderBookMinHeap.peek()).getPrice() >= buyOrderPrice) {
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
