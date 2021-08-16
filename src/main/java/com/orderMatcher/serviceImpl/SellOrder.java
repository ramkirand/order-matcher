package com.orderMatcher.serviceImpl;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import com.orderMatcher.model.StockOrder;
import com.orderMatcher.service.TradingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class SellOrder implements TradingStrategy {
    @Override public void execute(String inputCommand) {
        try {
            int sellOrderPrice = Integer.parseInt(inputCommand.split(Constants.AT)[1]);
            int sellOrderVolume = Integer.parseInt(inputCommand.split(Constants.AT)[0]);
            StockOrder sellOrder =
                StockOrder.builder().price(sellOrderPrice).volume(sellOrderVolume).build();
            StockOrder sellOrderClone = getSellOrderClone(sellOrderPrice, sellOrderVolume);

            allSellOrders.add(sellOrderClone);
            allSellOrders.sort(new OrderComparator());

            while (!buyOrderBookMaxHeap.isEmpty()
                && Objects.requireNonNull(buyOrderBookMaxHeap.peek()).getPrice() >= sellOrderPrice
                && sellOrder.getVolume() > 0) {
                StockOrder buyOrder = buyOrderBookMaxHeap.remove();
                int currentSale = Math.min(buyOrder.getVolume(), sellOrder.getVolume());
                System.out.println(Constants.TRADE + Constants.SPACE + currentSale + Constants.AT
                    + buyOrder.getPrice());
                buyOrder.setVolume(buyOrder.getVolume() - currentSale);
                sellOrder.setVolume(sellOrder.getVolume() - currentSale);
                if (buyOrder.getVolume() > 0)
                    buyOrderBookMaxHeap.add(buyOrder);

            }
            if (sellOrder.getVolume() > 0) {
                sellOrderBookMinHeap.add(sellOrder);
            }
        } catch (Exception ex) {
            log.info("In Sell stock:" + ex.getLocalizedMessage());
        }

    }

    private StockOrder getSellOrderClone(int sellOrderPrice, int sellOrderVolume) {
        return
            StockOrder.builder().price(sellOrderPrice).volume(sellOrderVolume)
                .transactionType(TransactionEnum.SELL.getCommand()).build();
    }
}
