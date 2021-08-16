package com.orderMatcher;

import com.orderMatcher.service.*;
import com.orderMatcher.serviceImpl.BuyOrder;
import com.orderMatcher.serviceImpl.PrintStockOrders;
import com.orderMatcher.serviceImpl.SellOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradeOrderTest {
    private TradingStrategy tradingStrategy;
    String[] commands = {
        //>>>>>>>>>>> Scenario 1 <<<<<<<<<<<<<<<<<<<<
        TransactionEnum.BUY + Constants.SPACE + "100@50",
        TransactionEnum.BUY + Constants.SPACE + "1000@25",
        TransactionEnum.SELL + Constants.SPACE + "500@35", TransactionEnum.PRINT.getCommand(),

        //>>>>>>>>>>> Scenario 2 <<<<<<<<<<<<<<<<<<<<
        TransactionEnum.SELL + Constants.SPACE + "100@55",
        TransactionEnum.SELL + Constants.SPACE + "500@67",
        TransactionEnum.SELL + Constants.SPACE + "200@88",
        TransactionEnum.BUY + Constants.SPACE + "1000@44",
        TransactionEnum.BUY + Constants.SPACE + "3000@33",

        TransactionEnum.PRINT.getCommand(),

        //>>>>>>>>>>> Scenario 3 <<<<<<<<<<<<<<<<<<<<
        TransactionEnum.SELL + Constants.SPACE + "100@10",
        TransactionEnum.SELL + Constants.SPACE + "100@15",
        TransactionEnum.BUY + Constants.SPACE + "120@17",

        TransactionEnum.PRINT.getCommand()};

    @Test
    public void shouldVerifyBuyOrderSizeList() {
        tradingStrategy = new BuyOrder();
        String buyStockCommand = "100@50";
        tradingStrategy.execute(buyStockCommand);
        buyStockCommand = "1000@50";
        tradingStrategy.execute(buyStockCommand);
        assertEquals(TradingStrategy.allBuyOrders.size(), 5);
    }

    @Test
    public void shouldTestValidScenario() {
        for (String cmd : commands) {
            if (cmd.startsWith("BUY")) {
                new Context(new BuyOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
            } else if (cmd.startsWith("SELL")) {
                new Context(new SellOrder()).executeStrategy(cmd.split(Constants.SPACE)[1]);
            } else if (cmd.startsWith("PRINT")) {
                new Context(new PrintStockOrders()).executeStrategy(cmd.trim());
            } else {
                System.exit(0);
            }
        }
    }
}
