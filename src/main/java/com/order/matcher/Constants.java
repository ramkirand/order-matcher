package com.order.matcher;

import com.order.matcher.model.StockOrder;

import java.util.Comparator;
public class Constants {
  public static final String BLANK_SPACE = " ";
  public static final String AT = "@";
  public static final int INITIAL_CAPACITY = 15;
  public static final String TRADE = "Trade";
  public static final String ENTER_INPUT_COMMAND = "Enter input command";
  public static final String INVALID_INPUT_COMMAND_OR_END = "Invalid input command or end";
  public static final Comparator<StockOrder> STOCK_ITEM_ASC_ORDER =
      Comparator.comparing(StockOrder::getPrice).thenComparing(StockOrder::getStockInstructionNUmber);

}
