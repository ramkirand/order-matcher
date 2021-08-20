package com.order.matcher.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {PrintStockOrder.class})
@ExtendWith(SpringExtension.class)
public class PrintStockOrderTest {
  @Mock private PrintStockOrder printStockOrderMock;

  @Test
  public void shouldCallPrintStockOrderOnce() {
    printStockOrderMock.execute("PRINT");
    verify(printStockOrderMock, times(1)).execute("PRINT");
  }
}
