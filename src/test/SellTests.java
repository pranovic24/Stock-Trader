package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.main.model.Trade;
import src.main.model.Trade.Stock;
import src.main.model.Trade.Type;
import src.main.model.account.Account;
import src.main.model.account.Personal;
import src.main.model.account.TFSA;

public class SellTests {
    
    Account[] accounts;

    @Before
    public void setup() {
        accounts = new Account[] {
            new Personal(1000), 
            new TFSA(1000)
        };
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].makeTrade(new Trade(Stock.FB, Type.MARKET_BUY, 10, 28.059999));
        }
    }

    @Test
    public void personalShares() {
        accounts[0].makeTrade(new Trade(Stock.FB, Type.MARKET_SELL, 6, 28.059999));
        assertEquals(accounts[0].getShares(Stock.FB), 4);
    }

    @Test
    public void tfsaShares() {
        accounts[1].makeTrade(new Trade(Stock.FB, Type.MARKET_SELL, 10, 28.059999));
        assertEquals(accounts[1].getShares(Stock.FB), 0);
    }

    @Test
    public void insufficientFunds() {
        accounts[0].makeTrade(new Trade(Stock.FB, Type.MARKET_SELL, 15, 28.059999));
        assertEquals(accounts[0].getShares(Stock.FB), 10);
    }

    @Test
    public void personalFunds() {
        double currentBalance = accounts[0].getFunds();
        accounts[0].makeTrade(new Trade(Stock.FB, Type.MARKET_SELL, 10, 28.059999));
        assertEquals(accounts[0].getFunds(), currentBalance + (10 * 28.059999) - (10 * 28.059999 * 0.05));
    }

    @Test
    public void tfsaFunds() {
        double currentBalance = accounts[1].getFunds();
        accounts[1].makeTrade(new Trade(Stock.FB, Type.MARKET_SELL, 10, 28.059999));
        assertEquals(accounts[1].getFunds(), currentBalance + (10 * 28.059999) - (10 * 28.059999 * 0.01));
    }

}
