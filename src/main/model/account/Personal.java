package src.main.model.account;

import src.main.model.Trade;
import src.main.model.Trade.Stock;

public class Personal extends Account {

    private static final double SELL_FEE = 0.05;
    
    public Personal(double funds) {
        super(funds);
    }

    public Personal(Personal source) {
        super(source);
    }

    @Override
    public Account clone() {
        // TODO Auto-generated method stub
        return new Personal(this);
    }

    @Override
    public boolean makeTrade(Trade trade) {
        // TODO Auto-generated method stub
        return trade.getType() == Trade.Type.MARKET_BUY ? executePurchase(trade, 0) : executeSales(trade, SELL_FEE);
    }
}
