package com.orderMatcher.service;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;

public class PrintStocks implements TradingStrategy {
    public void displayResults() {
        if (TradingStrategy.allSellOrders.size() == 0)
            return;
        TradingStrategy.allSellOrders.forEach(s -> {
            if (s.getVolume() > 0)
                System.out.println(
                    TransactionEnum.SELL.getStockMeta() + Constants.SPACE + s.getVolume()
                        + Constants.AT + s.getPrice());
        });
        if (TradingStrategy.allBuyOrders.size() == 0)
            return;
        TradingStrategy.allBuyOrders.forEach(b -> {
            if (b.getVolume() > 0)
                System.out.println(
                    TransactionEnum.BUY.getStockMeta() + Constants.SPACE + b.getVolume()
                        + Constants.AT + b.getPrice());
        });
    }

    @Override public void execute(String inputCommand) {
        try {
            if (inputCommand.startsWith(TransactionEnum.PRINT.getStockMeta())) {
                displayResults();
            }
        } catch (Exception ignored) {
        }

    }
}
