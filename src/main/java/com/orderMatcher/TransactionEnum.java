package com.orderMatcher;

public enum TransactionEnum {
    BUY("BUY"), SELL("SELL"), PRINT("PRINT");
    private final String stockMeta;

    TransactionEnum(String stockMeta) {
        this.stockMeta = stockMeta;
    }

    public String getStockMeta() {
        return stockMeta;
    }
}
