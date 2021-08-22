package com.order.matcher.util;

import com.order.matcher.Constants;
import com.order.matcher.model.StockOrder;
import lombok.experimental.UtilityClass;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
public class OrderMatcherUtil {
  public PriorityQueue<StockOrder> sellStockOrderMinHeap =
      new PriorityQueue<>(Constants.INITIAL_CAPACITY, Constants.STOCK_ITEM_ASC_ORDER);
  public PriorityQueue<StockOrder> buyStockOrderMaxHeap =
      new PriorityQueue<>(Constants.INITIAL_CAPACITY, Constants.STOCK_ITEM_ASC_ORDER.reversed());
    public AtomicInteger instructionNum = new AtomicInteger(0);
}
