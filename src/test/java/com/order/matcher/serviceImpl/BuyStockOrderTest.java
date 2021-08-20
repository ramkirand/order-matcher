package com.order.matcher.serviceImpl;

import com.order.matcher.service.TradingStrategy;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BuyStockOrder.class})
@ExtendWith(SpringExtension.class)
public class BuyStockOrderTest {
  @Autowired private TradingStrategy tradingStrategy;

  @BeforeEach
  public void setUp() {
    tradingStrategy = new BuyStockOrder();
  }

  @Test
  public void testExecute() {
    String command = "100@10";
    assertThat(TradingStrategy.buyStockOrderMaxHeap.size() == 0);
    tradingStrategy.execute(command);
    assertThat(TradingStrategy.buyStockOrderMaxHeap.size() == 1);
  }
}
