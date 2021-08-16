package com.orderMatcher.service;

import com.orderMatcher.Constants;
import com.orderMatcher.model.StockOrder;
import com.orderMatcher.serviceImpl.StockOrderComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
