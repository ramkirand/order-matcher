package com.orderMatcher.service;

import com.orderMatcher.model.Order;

import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {
    @Override public int compare(Order o1, Order o2) {
        if (o1.getPrice().compareTo(o2.getPrice()) > 0)
            return 1;
        else if (o1.getPrice().compareTo(o2.getPrice())< 0)
            return -1;
        else
            return 0;
    }
}
