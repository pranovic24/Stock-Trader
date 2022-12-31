package src.main.model.account;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import src.main.model.Trade;
import src.main.model.Trade.Stock;
import src.main.utils.Color;

public abstract class Account {
    // Polymorphism --> Map --> HashMap
    private Map<Stock, Integer> portfolio;
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
        this.portfolio = copyMap(source.portfolio);
    }

    // shallow copy of portfolio hashMap
    private Map<Stock, Integer> copyMap(Map<Stock, Integer> map) {
        return map.entrySet().stream()
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
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

    public abstract Account clone();

    public abstract boolean makeTrade(Trade trade);

    protected boolean executePurchase(Trade trade, double fee) {
        double total = trade.getShares() * trade.getPrice();
        if (total <= this.getFunds()) {
            this.setFunds(this.getFunds() - total - (total * fee));
            addShares(trade);
            return true;
        }
        return false;
    }

    protected boolean executeSales(Trade trade, double fee) {
        if (trade.getShares() <= this.getShares(trade.getStock())) {
            this.setShares(trade.getStock(), getCurrentShares(trade.getStock()) - trade.getShares());
            addFunds(trade, fee);
            return true;
        }
        return false;
    }

    private void addShares(Trade trade) {
        int currentShares = getCurrentShares(trade.getStock());
        this.setShares(trade.getStock(), currentShares + trade.getShares());
    }

    private int getCurrentShares(Stock stock) {
        return portfolio.get(stock) == null ? 0 : portfolio.get(stock);
    }

    private void addFunds(Trade trade, double fee) {
        double total = trade.getShares() * trade.getPrice();
        this.setFunds(this.getFunds() + total - (total * fee));
    }

    private String displayPortfolio() {
        String text = "";
        for (Map.Entry<Stock, Integer> entry : portfolio.entrySet()) {
            text += "  " + Color.BLUE + entry.getKey() + "\t\t";
            text += Color.GREEN + entry.getValue();
            text += "\n";
        }
        return text;
    }

    private double round(double amount) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(amount));
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "\n  Stock\t\t" + Color.RESET + "Shares" +
            "\n\n" + displayPortfolio() + Color.RESET + 
            "\n  Funds Left\t" + Color.GREEN + "$" + round(this.getFunds()) + Color.RESET; 
    }

}
