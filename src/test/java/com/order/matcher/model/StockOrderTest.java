package com.order.matcher.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockOrderTest {

  @Test
  public void testCanEqual() {
    assertFalse((new StockOrder(1, 1, "Transaction Type", 1)).canEqual("Other"));
  }

  @Test
  public void testCanEqual2() {
    StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 1);
    assertTrue(stockOrder.canEqual(new StockOrder(1, 1, "Transaction Type", 1)));
  }

  @Test
  public void testConstructor() {
    StockOrder actualStockOrder = new StockOrder(1, 1, "Transaction Type", 1);
    actualStockOrder.setPrice(1);
    actualStockOrder.setStockInstructionNUmber(1);
    actualStockOrder.setTransactionType("Transaction Type");
    actualStockOrder.setVolume(1);
    assertEquals(1, actualStockOrder.getPrice());
    assertEquals(1, actualStockOrder.getStockInstructionNUmber());
    assertEquals("Transaction Type", actualStockOrder.getTransactionType());
    assertEquals(1, actualStockOrder.getVolume());
    assertEquals(
        "StockOrder(price=1, volume=1, transactionType=Transaction Type, stockInstructionNUmber=1)",
        actualStockOrder.toString());
  }

  @Test
  public void testEquals() {
    assertNotEquals(null, (new StockOrder(1, 1, "Transaction Type", 1)));
  }

  @Test
  public void testEquals2() {
    StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 1);
    assertEquals(stockOrder, stockOrder);
    int expectedHashCodeResult = stockOrder.hashCode();
    assertEquals(expectedHashCodeResult, stockOrder.hashCode());
  }

  @Test
  public void testEquals3() {
    StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 1);
    StockOrder stockOrder1 = new StockOrder(1, 1, "Transaction Type", 1);

    assertEquals(stockOrder, stockOrder1);
    int expectedHashCodeResult = stockOrder.hashCode();
    assertEquals(expectedHashCodeResult, stockOrder1.hashCode());
  }

  @Test
  public void testEquals4() {
    StockOrder stockOrder = new StockOrder(0, 1, "Transaction Type", 1);
    assertNotEquals(stockOrder, new StockOrder(1, 1, "Transaction Type", 1));
  }

  @Test
  public void testEquals5() {
    StockOrder stockOrder = new StockOrder(1, 0, "Transaction Type", 1);
    assertNotEquals(stockOrder, new StockOrder(1, 1, "Transaction Type", 1));
  }

  @Test
  public void testEquals6() {
    StockOrder stockOrder = new StockOrder(1, 1, null, 1);
    assertNotEquals(stockOrder, new StockOrder(1, 1, "Transaction Type", 1));
  }

  @Test
  public void testEquals7() {
    StockOrder stockOrder = new StockOrder(1, 1, "com.order.matcher.model.StockOrder", 1);
    assertNotEquals(stockOrder, new StockOrder(1, 1, "Transaction Type", 1));
  }

  @Test
  public void testEquals8() {
    StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 0);
    assertNotEquals(stockOrder, new StockOrder(1, 1, "Transaction Type", 1));
  }

  @Test
  public void testEquals9() {
    StockOrder stockOrder = new StockOrder(1, 1, null, 1);
    StockOrder stockOrder1 = new StockOrder(1, 1, null, 1);

    assertEquals(stockOrder, stockOrder1);
    int expectedHashCodeResult = stockOrder.hashCode();
    assertEquals(expectedHashCodeResult, stockOrder1.hashCode());
  }
}
