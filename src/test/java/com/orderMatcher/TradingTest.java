package com.orderMatcher;

import com.orderMatcher.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class) @SpringBootTest public class TradingTest {
    private TradingStrategy tradingStrategy;
    String[] commands = {
        TransactionEnum.BUY + Constants.SPACE + "100@50",
        TransactionEnum.BUY + Constants.SPACE + "1000@25",
        TransactionEnum.SELL + Constants.SPACE + "500@35",
        TransactionEnum.PRINT.getStockMeta()};

    @Test
    public void shouldVerifyBuyOrderSizeList() {
        tradingStrategy = new BuyStock();
        String buyStockCommand = "100@50";
        tradingStrategy.execute(buyStockCommand);
        buyStockCommand = "1000@50";
        tradingStrategy.execute(buyStockCommand);
        assertEquals(TradingStrategy.allBuyOrders.size(), 2);
    }

    @Test public void shouldTestValidScenario() {
        Context context = null;
        for (String cmd : commands) {
            if (cmd.startsWith("BUY")) {
                context = new Context(new BuyStock());
                context.executeStrategy(cmd.split(Constants.SPACE)[1]);
            } else if (cmd.startsWith("SELL")) {
                context = new Context(new SellStock());
                context.executeStrategy(cmd.split(Constants.SPACE)[1]);
            } else if (cmd.startsWith("PRINT")) {
                context = new Context(new PrintStocks());
                context.executeStrategy(cmd.trim());
            } else {
                System.exit(0);
            }
        }
    }
}
