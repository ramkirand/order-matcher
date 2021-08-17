package com.orderMatcher.serviceImpl;

import com.orderMatcher.service.TradingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {SellStockOrder.class})
@ExtendWith(SpringExtension.class)
public class SellStockOrderTest {
  @Autowired private TradingStrategy tradingStrategy;

  @BeforeEach
  public void setUp() {
    tradingStrategy = new SellStockOrder();
  }

  @Test
  public void testExecute() {
    String command = "500@10";
    assertThat(TradingStrategy.buyStockOrderMaxHeap.size() == 0);
    tradingStrategy.execute(command);
    assertThat(TradingStrategy.buyStockOrderMaxHeap.size() == 1);
  }
}
