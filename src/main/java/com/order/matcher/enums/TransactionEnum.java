package com.order.matcher.enums;

public enum TransactionEnum {
  BUY("BUY"),
  SELL("SELL"),
  PRINT("PRINT");
  private final String command;

  TransactionEnum(String stockMeta) {
    this.command = stockMeta;
  }

  public String getCommand() {
    return command;
  }
}
