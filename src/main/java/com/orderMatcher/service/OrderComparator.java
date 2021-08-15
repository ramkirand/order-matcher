package com.orderMatcher.service;

import com.orderMatcher.model.Stock;

import java.util.Comparator;

public class OrderComparator implements Comparator<Stock> {
	@Override
	public int compare(Stock o1, Stock o2) {
		return Integer.compare(o1.getPrice() - o2.getPrice(), 0);
	}
}
