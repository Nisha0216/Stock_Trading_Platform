import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }
}

class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double balance;

    public Portfolio(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost > balance) {
            System.out.println("Insufficient funds!");
            return;
        }
        balance -= cost;
        holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
    }

    public void sellStock(Stock stock, int quantity) {
        if (!holdings.containsKey(stock.getSymbol()) || holdings.get(stock.getSymbol()) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        holdings.put(stock.getSymbol(), holdings.get(stock.getSymbol()) - quantity);
        balance += stock.getPrice() * quantity;
        System.out.println("Sold " + quantity + " shares of " + stock.getSymbol());
    }

    public void showPortfolio() {
        System.out.println("Portfolio Holdings: " + holdings);
        System.out.println("Available Balance: " + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Stock apple = new Stock("AAPL", 150.0);
        Stock google = new Stock("GOOGL", 2800.0);
        Portfolio myPortfolio = new Portfolio(5000.0);

        myPortfolio.buyStock(apple, 10);
        myPortfolio.buyStock(google, 1);
        myPortfolio.showPortfolio();
        
        apple.updatePrice(160.0);
        myPortfolio.sellStock(apple, 5);
        myPortfolio.showPortfolio();
    }
}
