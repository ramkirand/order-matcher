package com.order.matcher.serviceImpl;

import com.order.matcher.model.StockOrder;

import java.util.Comparator;

public class StockOrderComparator implements Comparator<StockOrder> {
  @Override
  public int compare(StockOrder o1, StockOrder o2) {
    if (o1.getPrice() == o2.getPrice()) return o1.getInstructionNum() - o2.getInstructionNum();
    return Integer.compare(o1.getPrice() - o2.getPrice(), 0);
  }
}
