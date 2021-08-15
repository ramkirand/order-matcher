package com.orderMatcher.service;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PrintStocks {
	public void displayResults() {
		TradingStrategy.allSellOrders.forEach(s -> {
			if (s.getVolume() > 0)
				log.info(TransactionEnum.SELL.getStockMeta() + Constants.SPACE + s.getVolume() + Constants.AT
						+ s.getPrice());
		});
		TradingStrategy.allBuyOrders.forEach(b -> {
			if (b.getVolume() > 0)
				log.info(TransactionEnum.BUY.getStockMeta() + Constants.SPACE + b.getVolume() + Constants.AT
						+ b.getPrice());
		});
	}
}
