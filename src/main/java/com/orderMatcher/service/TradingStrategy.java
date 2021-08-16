package com.orderMatcher.service;

import com.orderMatcher.Constants;
import com.orderMatcher.model.StockOrder;
import com.orderMatcher.serviceImpl.OrderComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public interface TradingStrategy {
    PriorityQueue<StockOrder> sellOrderBookMinHeap =
        new PriorityQueue<>(Constants.INITIAL_CAPACITY, new OrderComparator());
    PriorityQueue<StockOrder> buyOrderBookMaxHeap = new PriorityQueue<>(Constants.INITIAL_CAPACITY,
        Collections.reverseOrder(new OrderComparator()));
    List<StockOrder> allBuyOrders = new ArrayList<>();
    List<StockOrder> allSellOrders = new ArrayList<>();

    void execute(String inputCommand);
}
