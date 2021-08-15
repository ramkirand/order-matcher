package com.orderMatcher.service;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import com.orderMatcher.model.Stock;
import java.util.Collections;

public class SellStock implements TradingStrategy {
    @Override
		public void execute(String inputCommand) {
        int sellOrderPrice = Integer.parseInt(inputCommand.split(Constants.AT)[1]);
        int sellOrderVolume = Integer.parseInt(inputCommand.split(Constants.AT)[0]);
        Stock sellOrder = new Stock();
        sellOrder.setPrice(sellOrderPrice);
        sellOrder.setVolume(sellOrderVolume);
        sellOrder.setTransactionType(TransactionEnum.SELL.getStockMeta());

        Stock sellOrderClone = getSellOrderClone(sellOrderPrice, sellOrderVolume);

        allSellOrders.add(sellOrderClone);
        Collections.sort(allSellOrders, new OrderComparator());

        while (!buyOrderBookMaxHeap.isEmpty()
            && buyOrderBookMaxHeap.peek().getPrice() >= sellOrderPrice
            && sellOrder.getVolume() > 0) {
            Stock buyOrder = buyOrderBookMaxHeap.remove();
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
    }

    private Stock getSellOrderClone(int sellOrderPrice, int sellOrderVolume) {
        Stock sellOrderClone = new Stock();
        sellOrderClone.setPrice(sellOrderPrice);
        sellOrderClone.setVolume(sellOrderVolume);
        sellOrderClone.setTransactionType(TransactionEnum.SELL.getStockMeta());
        return sellOrderClone;
    }

}
