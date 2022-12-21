package src.main.model.account;

import java.util.HashMap;

import src.main.model.Trade.Stock;

public abstract class Account {
    private HashMap<Stock, Integer> portfolio;
    private double funds;

    public Account(double funds) {
        this.funds = funds;
        this.portfolio = new HashMap<Stock, Integer>();
        this.portfolio.put(Stock.AAPL, 0);
        this.portfolio.put(Stock.FB, 0);
        this.portfolio.put(Stock.GOOG, 0);
        this.portfolio.put(Stock.TSLA, 0);
    }

    public Account(Account source) {
        this.funds = source.funds;
    }

    public double getFunds() {
        return this.funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public Integer getShares(Stock stock) {
        return this.portfolio.get(stock);
    }

    public void setShares(Stock stock, Integer shares) {
        this.portfolio.put(stock, shares);
    }

}
