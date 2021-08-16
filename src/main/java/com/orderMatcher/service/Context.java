package com.orderMatcher.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Context {
  private TradingStrategy tradingStrategy;

  public void executeStrategy(String command) {
    tradingStrategy.execute(command);
  }
}
