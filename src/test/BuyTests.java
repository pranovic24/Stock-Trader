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

public class BuyTests {
    
    Account[] accounts;

    @Before
    public void setup() {
        accounts = new Account[] {
            new Personal(1000), 
            new TFSA(1000)
        };
    }

    @Test
    public void personalShares() {
        accounts[0].makeTrade(new Trade(Stock.FB, Type.MARKET_BUY, 10, 28.059999));
        assertEquals(accounts[0].getShares(Stock.FB), 10);
    }

    @Test
    public void tfsaShares() {
        accounts[1].makeTrade(new Trade(Stock.FB, Type.MARKET_BUY, 10, 28.059999));
        assertEquals(accounts[1].getShares(Stock.FB), 10);
    }

    @Test
    public void insufficientFunds() {
        accounts[0].makeTrade(new Trade(Stock.FB, Type.MARKET_BUY, 1000, 28.059999));
        assertEquals(accounts[0].getShares(Stock.FB), 0);
    }

    @Test
    public void personalFunds() {
        accounts[0].makeTrade(new Trade(Stock.FB, Type.MARKET_BUY, 10, 28.059999));
        assertEquals(accounts[0].getFunds(), 1000 - 28.059999 * 10);
    }

    @Test
    public void tfsaFunds() {
        accounts[1].makeTrade(new Trade(Stock.FB, Type.MARKET_BUY, 10, 28.059999));
        assertEquals(accounts[1].getFunds(), 1000 - 28.059999 * 10 - 28.059999 * 10 * 0.01);
    }

}
