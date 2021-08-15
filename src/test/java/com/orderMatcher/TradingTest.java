package com.orderMatcher;

import com.orderMatcher.service.BuyStock;
import com.orderMatcher.service.SellStock;
import com.orderMatcher.service.TradingStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class) @SpringBootTest public class TradingTest {
    private TradingStrategy tradingStrategy;

    @Test public void shouldVerifySellOrderSizeList() {
        tradingStrategy = new SellStock();
        String sellStockCommand = TransactionEnum.SELL + Constants.SPACE + "500@35";
        tradingStrategy.execute(sellStockCommand);
        assertEquals(TradingStrategy.allSellOrders.size(), 1);
    }

    @Test public void shouldVerifyBuyOrderSizeList() {
        tradingStrategy = new BuyStock();
        String buyStockCommand = TransactionEnum.BUY + Constants.SPACE + "100@50";
        tradingStrategy.execute(buyStockCommand);
        buyStockCommand = TransactionEnum.BUY + Constants.SPACE + "100@50";
        tradingStrategy.execute(buyStockCommand);
        assertEquals(TradingStrategy.allBuyOrders.size(), 2);

    }
}
