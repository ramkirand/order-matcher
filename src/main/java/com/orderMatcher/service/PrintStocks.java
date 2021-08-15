package com.orderMatcher.service;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PrintStocks {
    private TradingStrategy  tradingStrategy;
    public PrintStocks(TradingStrategy tradingStrategy){
        this.tradingStrategy = tradingStrategy;
    }
    public void displayResults(){
        tradingStrategy.allSellOrders.forEach(s -> {
            if (s.getVolume() > 0)
                log.info(TransactionEnum.SELL.getStockMeta() + Constants.SPACE + s.getVolume()
                    + Constants.AT + s.getPrice());
        });
        tradingStrategy.allBuyOrders.forEach(b -> {
            if (b.getVolume() > 0)
                log.info(TransactionEnum.BUY.getStockMeta() + Constants.SPACE + b.getVolume()
                    + Constants.AT + b.getPrice());
        });
    }
}
