package src.main.model.account;

import src.main.model.Trade;

public class TFSA extends Account {

    private static final double TRADE_FEE = 0.01;
    
    public TFSA(double funds) {
        super(funds);
    }

    public TFSA(TFSA source) {
        super(source);
    }

    @Override
    public Account clone() {
        // TODO Auto-generated method stub
        return new TFSA(this);
    }

    @Override
    public boolean makeTrade(Trade trade) {
        // TODO Auto-generated method stub
        return trade.getType() == Trade.Type.MARKET_BUY ? super.executePurchase(trade, TRADE_FEE) : super.executeSales(trade, TRADE_FEE);
    }
}
