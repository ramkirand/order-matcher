package com.orderMatcher.service;

import lombok.Getter;

@Getter
public class Context {

	private TradingStrategy tradingStrategy;

	public Context(TradingStrategy tradingStrategy) {
		this.tradingStrategy = tradingStrategy;
	}

	public void executeStrategy(String command) {
		tradingStrategy.execute(command);
	}
}
