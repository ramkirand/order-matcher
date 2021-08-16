package com.orderMatcher.model;

import lombok.*;

@Data
@Builder
public class StockOrder {
  private int price;
  private int volume;
  private String transactionType;
  private int instructionNum;
}
