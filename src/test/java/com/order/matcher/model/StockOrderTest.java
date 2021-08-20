package com.order.matcher.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StockOrderTest {

    @Test public void testCanEqual() {
        assertFalse((new StockOrder(1, 1, "Transaction Type", 10)).canEqual("Other"));
    }

    @Test public void testCanEqual2() {
        StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 10);
        assertTrue(stockOrder.canEqual(new StockOrder(1, 1, "Transaction Type", 10)));
    }

    @Test public void testConstructor() {
        StockOrder actualStockOrder = new StockOrder(1, 1, "Transaction Type", 10);
        actualStockOrder.setInstructionNum(10);
        actualStockOrder.setPrice(1);
        actualStockOrder.setTransactionType("Transaction Type");
        actualStockOrder.setVolume(1);
        assertEquals(10, actualStockOrder.getInstructionNum());
        assertEquals(1, actualStockOrder.getPrice());
        assertEquals("Transaction Type", actualStockOrder.getTransactionType());
        assertEquals(1, actualStockOrder.getVolume());
        assertEquals(
            "StockOrder(price=1, volume=1, transactionType=Transaction Type, instructionNum=10)",
            actualStockOrder.toString());
    }

    @Test public void testEquals() {
        assertFalse((new StockOrder(1, 1, "Transaction Type", 10)).equals(null));
        assertFalse((new StockOrder(1, 1, "Transaction Type", 10)).equals("Different type to StockOrder"));
    }

    @Test public void testEquals2() {
        StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 10);
        assertTrue(stockOrder.equals(stockOrder));
        int expectedHashCodeResult = stockOrder.hashCode();
        assertEquals(expectedHashCodeResult, stockOrder.hashCode());
    }

    @Test public void testEquals3() {
        StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 10);
        StockOrder stockOrder1 = new StockOrder(1, 1, "Transaction Type", 10);

        assertTrue(stockOrder.equals(stockOrder1));
        int expectedHashCodeResult = stockOrder.hashCode();
        assertEquals(expectedHashCodeResult, stockOrder1.hashCode());
    }

    @Test public void testEquals4() {
        StockOrder stockOrder = new StockOrder(0, 1, "Transaction Type", 10);
        assertFalse(stockOrder.equals(new StockOrder(1, 1, "Transaction Type", 10)));
    }

    @Test public void testEquals5() {
        StockOrder stockOrder = new StockOrder(1, 0, "Transaction Type", 10);
        assertFalse(stockOrder.equals(new StockOrder(1, 1, "Transaction Type", 10)));
    }

    @Test public void testEquals6() {
        StockOrder stockOrder = new StockOrder(1, 1, null, 10);
        assertFalse(stockOrder.equals(new StockOrder(1, 1, "Transaction Type", 10)));
    }

    @Test public void testEquals7() {
        StockOrder stockOrder = new StockOrder(1, 1, "com.order.matcher.model.StockOrder", 10);
        assertFalse(stockOrder.equals(new StockOrder(1, 1, "Transaction Type", 10)));
    }

    @Test public void testEquals8() {
        StockOrder stockOrder = new StockOrder(1, 1, "Transaction Type", 0);
        assertFalse(stockOrder.equals(new StockOrder(1, 1, "Transaction Type", 10)));
    }

    @Test public void testEquals9() {
        StockOrder stockOrder = new StockOrder(1, 1, null, 10);
        StockOrder stockOrder1 = new StockOrder(1, 1, null, 10);

        assertTrue(stockOrder.equals(stockOrder1));
        int expectedHashCodeResult = stockOrder.hashCode();
        assertEquals(expectedHashCodeResult, stockOrder1.hashCode());
    }
}

