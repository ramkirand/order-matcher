package com.orderMatcher.service;

import com.orderMatcher.Constants;
import com.orderMatcher.model.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public interface TradingStrategy {
    PriorityQueue<Stock> sellOrderBookMinHeap =
        new PriorityQueue<>(Constants.INITIAL_CAPACITY, new OrderComparator());
    PriorityQueue<Stock> buyOrderBookMaxHeap = new PriorityQueue<>(Constants.INITIAL_CAPACITY,
        Collections.reverseOrder(new OrderComparator()));
    List<Stock> allBuyOrders = new ArrayList<>();
    List<Stock> allSellOrders = new ArrayList<>();

    void execute(String inputCommand);
}
