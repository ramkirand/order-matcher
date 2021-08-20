package com.order.matcher.service;

import com.order.matcher.Constants;
import com.order.matcher.model.StockOrder;
import com.order.matcher.serviceImpl.StockOrderComparator;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public interface TradingStrategy {
  PriorityQueue<StockOrder> sellStockOrderMinHeap =
      new PriorityQueue<>(Constants.INITIAL_CAPACITY, new StockOrderComparator());
  PriorityQueue<StockOrder> buyStockOrderMaxHeap =
      new PriorityQueue<>(
          Constants.INITIAL_CAPACITY, Collections.reverseOrder(new StockOrderComparator()));
  AtomicInteger instructionNum = new AtomicInteger(0);

  void execute(String inputCommand);
}
