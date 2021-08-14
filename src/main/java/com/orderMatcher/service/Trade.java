package com.orderMatcher.service;

import com.orderMatcher.CommandEnum;
import com.orderMatcher.Constants;
import com.orderMatcher.model.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.PriorityQueue;

@Slf4j
public class Trade {
    private final PriorityQueue<Order> sellOrderMinHeap;
    private final PriorityQueue<Order> buyOrderMaxHeap;

    public Trade() {
        sellOrderMinHeap = new PriorityQueue<>(Constants.INITIAL_CAPACITY, new OrderComparator());
        buyOrderMaxHeap = new PriorityQueue<>(Constants.INITIAL_CAPACITY,
            Collections.reverseOrder(new OrderComparator()));
    }

    public void executeCommand(String inputCommand) {
        if (inputCommand.startsWith(CommandEnum.SELL.toString())) {
            String orderType = inputCommand.split(Constants.SPACE)[0];
            String data = inputCommand.split(Constants.SPACE)[1];
            String sp = data.split(Constants.AT)[1];
            String sv = data.split(Constants.AT)[0];
            if (buyOrderMaxHeap.isEmpty()) {
                Order sellOrder = new Order();
                sellOrder.setPrice(sp);
                sellOrder.setVolume(sv);
                buyOrderMaxHeap.add(sellOrder);
            }
            while (!buyOrderMaxHeap.isEmpty()
                && Integer.parseInt(buyOrderMaxHeap.peek().getPrice()) == Integer.parseInt(sp)) {
                buyOrderMaxHeap.remove();
            }
            buildResult(sp, sv, orderType);
        }
        else if (inputCommand.startsWith(CommandEnum.BUY.toString())) {
            String orderType = inputCommand.split(Constants.SPACE)[0];
            String data = inputCommand.split(Constants.SPACE)[1];
            String bp = data.split(Constants.AT)[1];
            String bv = data.split(Constants.AT)[0];
            Order buyOrder = new Order();
            buyOrder.setPrice(bp);
            buyOrder.setVolume(bv);
            sellOrderMinHeap.add(buyOrder);
            while (!sellOrderMinHeap.isEmpty()
                && Integer.parseInt(sellOrderMinHeap.peek().getPrice()) == Integer.parseInt(bp)) {
                sellOrderMinHeap.remove();
            }
            buildResult(bp, bv, orderType);
        } else if (inputCommand.equals(CommandEnum.PRINT.toString())) {
            log.info(Constants.DISPLAY_SELL_ORDER_MIN_HEAP + sellOrderMinHeap);
            log.info(Constants.DISPLAY_BUY_ORDER_MAX_HEAP + buyOrderMaxHeap);
        } else {
            System.exit(0);
        }
    }

    private String buildResult(String price, String volume, String type) {
        StringBuilder ans = new StringBuilder();
        ans.append(type).append(Constants.SPACE).append(volume).append(Constants.AT).append(price);
        return ans.toString();
    }


    public static void main(String args[]) {
        Trade trade = new Trade();
        trade.executeCommand("BUY 1000@25");
        trade.executeCommand("SELL 500@30");
        trade.executeCommand("PRINT");
        trade.executeCommand("exit");
        //        while (true) {
        //            System.out.println("Enter data");
        //            Scanner scanner = new Scanner((System.in));
        //            trade.execute(scanner.next());
        //            trade.execute(scanner.next());
        //        }
    }
}
