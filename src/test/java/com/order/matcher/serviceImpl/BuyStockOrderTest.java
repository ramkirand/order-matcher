package com.order.matcher.serviceImpl;

import com.order.matcher.service.TradingStrategy;
import com.order.matcher.util.OrderMatcherUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {BuyStockOrder.class})
@ExtendWith(SpringExtension.class)
public class BuyStockOrderTest {
  @Autowired private TradingStrategy tradingStrategy;
  @Mock private BuyStockOrder buyStockOrderMock;

  @BeforeEach
  public void setUp() {
    tradingStrategy = new BuyStockOrder();
  }

  @Test
  public void testExecute() {
    String command = "100@10";
    assertThat(OrderMatcherUtil.buyStockOrderMaxHeap.size() == 0);
    tradingStrategy.execute(command);
    assertThat(OrderMatcherUtil.buyStockOrderMaxHeap.size() == 1);
  }

  @Test
  public void shouldCallBuyStockOrderOnce() {
    buyStockOrderMock.execute("BUY");
    verify(buyStockOrderMock, times(1)).execute("BUY");
  }
}
