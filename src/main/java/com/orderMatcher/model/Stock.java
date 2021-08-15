package com.orderMatcher.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @EqualsAndHashCode @NoArgsConstructor
public class Stock {
    private int price;
    private int volume;
    private String transactionType;
}
