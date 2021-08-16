package com.orderMatcher.serviceImpl;

import com.orderMatcher.model.StockOrder;

import java.util.Comparator;

public class OrderComparator implements Comparator<StockOrder> {
	@Override
	public int compare(StockOrder o1, StockOrder o2) {
		return Integer.compare(o1.getPrice() - o2.getPrice(), 0);
	}
}
