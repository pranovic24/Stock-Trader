package src.main.model.account;

import src.main.model.Trade.Stock;

public class Personal extends Account {
    
    public Personal(double funds) {
        super(funds);
    }

    public Personal(Personal source) {
        super(source);
    }
}
