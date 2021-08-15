package com.orderMatcher.service;

import java.util.Collections;

import com.orderMatcher.Constants;
import com.orderMatcher.TransactionEnum;
import com.orderMatcher.model.Stock;

public class BuyStock implements TradingStrategy {
	@Override
	public void execute(String inputCommand) {
		Stock buyOrder = null;
		String data = inputCommand.split(Constants.SPACE)[1];
		int buyOrderPrice = Integer.parseInt(data.split(Constants.AT)[1]);
		int buyOrderVolume = Integer.parseInt(data.split(Constants.AT)[0]);
		if (sellOrderBookMinHeap.isEmpty()) {
			buyOrder = new Stock();
			buyOrder.setPrice(buyOrderPrice);
			buyOrder.setVolume(buyOrderVolume);
			buyOrder.setTransactionType(TransactionEnum.BUY.getStockMeta());
			buyOrderBookMaxHeap.add(buyOrder);
		} else {
			while (!sellOrderBookMinHeap.isEmpty() && sellOrderBookMinHeap.peek().getPrice() >= buyOrderPrice) {
				sellOrderBookMinHeap.remove();
			}
		}
		if (buyOrder != null && buyOrder.getVolume() > 0) {
			allBuyOrders.add(buyOrder);
			Collections.sort(allBuyOrders, new OrderComparator());
		}
	}
}
