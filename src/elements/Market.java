package elements;

import java.util.PriorityQueue;
import java.util.ArrayList;
import executable.*;

/**
 * The market in the system where buying and selling operations occur.
 * @author Hakan
 *
 */
public class Market {
	
	private PriorityQueue<SellingOrder> sellingOrders;
	private PriorityQueue<BuyingOrder> buyingOrders;
	private ArrayList<Transaction> transactions;
	
	double fee;
	/**
	 * The constructor for the market.
	 * @param fee The fee rate for transactions.
	 */
	public Market(int fee) {
		this.fee = (double) fee;
		sellingOrders = new PriorityQueue<SellingOrder>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		transactions = new ArrayList<Transaction>();
	}
	
	/**
	 * Creates and adds selling orders to the relevant queue.
	 * @param order The order that is to be added to the relevant queue.
	 */
	public void giveSellOrder(SellingOrder order) {
		
		this.sellingOrders.add(order);
		checkTransactions(Main.traders);
		return;
	}
	/**
	 * Creates and adds buying orders to the relevant queue.
	 * @param order The order that is to be added to the relevant queue.
	 */
	public void giveBuyOrder(BuyingOrder order) {
		
		this.buyingOrders.add(order);
		checkTransactions(Main.traders);
		return;
		
	}
	
	/**
	 * It sets the buying and selling orders in between the given price by conducting oper market operations.
	 * @param price The price to set.
	 */
	public void makeOpenMarketOperation(double price) {
		while ((getMarketBuyPrice() <= price && getMarketBuyPrice()!=0) || getMarketSellPrice() >= price) {
			if(getMarketBuyPrice() <= price && getMarketBuyPrice()!=0) {
				
				Main.traders.get(0).setDollars(Double.MAX_VALUE);
				Main.traders.get(0).buy(sellingOrders.peek().amount, sellingOrders.peek().price, this);
			}
			
			if(getMarketSellPrice() >= price) {
				Main.traders.get(0).setCoins(Double.MAX_VALUE);
				Main.traders.get(0).sell(buyingOrders.peek().amount, buyingOrders.peek().price, this);
			}
			
			
		}
		
		
	}
	/**
	 * Checks the selling and buying queues after every order is placed if there are any overlaps between this orders. Creates the relevant transactions if the orders are overlapped.
	 * @param traders The traders array.
	 */
	
	public void checkTransactions(ArrayList<Trader> traders) {
		while(getMarketBuyPrice() <= getMarketSellPrice() && (numBuyingOrders()!=0 && numSellingOrders()!=0)) {
			SellingOrder sellOrder = sellingOrders.poll();
			BuyingOrder buyOrder = buyingOrders.poll();
			int sellerID = sellOrder.traderID;
			int buyerID = buyOrder.traderID;
			transactions.add(new Transaction(sellOrder, buyOrder));
			
			if(sellOrder.amount < buyOrder.amount) {
				traders.get(buyerID).depositCoins(sellOrder.amount);
				traders.get(buyerID).unblockDollars(buyOrder.amount*buyOrder.price);
				traders.get(buyerID).withdraw(sellOrder.amount*sellOrder.price);
				traders.get(buyerID).buy((buyOrder.amount-sellOrder.amount) , buyOrder.price, this);
				
				traders.get(sellerID).deposit(sellOrder.amount*sellOrder.price*(1-(fee/1000)));
				traders.get(sellerID).unblockCoins(sellOrder.amount);
				traders.get(sellerID).withdrawCoins(sellOrder.amount);
				
			}
			
			if (sellOrder.amount == buyOrder.amount) {
				traders.get(buyerID).depositCoins(sellOrder.amount);
				traders.get(buyerID).unblockDollars(buyOrder.amount*buyOrder.price);
				traders.get(buyerID).withdraw(sellOrder.amount*sellOrder.price);
				
				traders.get(sellerID).deposit(sellOrder.amount*sellOrder.price*(1-(fee/1000)));
				traders.get(sellerID).unblockCoins(sellOrder.amount);
				traders.get(sellerID).withdrawCoins(sellOrder.amount);
				
				
			}
			
			if (sellOrder.amount > buyOrder.amount) {
				traders.get(buyerID).depositCoins(buyOrder.amount);
				traders.get(buyerID).unblockDollars(buyOrder.amount*buyOrder.price);
				traders.get(buyerID).withdraw(buyOrder.amount*sellOrder.price);
				
				traders.get(sellerID).deposit(buyOrder.amount*sellOrder.price*(1-(fee/1000)));
				traders.get(sellerID).unblockCoins(sellOrder.amount);
				traders.get(sellerID).withdrawCoins(buyOrder.amount);
				traders.get(sellerID).sell((sellOrder.amount - buyOrder.amount), sellOrder.price, this);
				
			}
		}
		
		
	}
	/**
	 * Returns the size of sellingOrders queue.
	 * @return The size of sellingOrders queue.
	 */
	public int numSellingOrders() {
		return sellingOrders.size();
	}
	/**
	 * Returns the size of buyingOrders queue.
	 * @return The size of buyingOrders queue.
	 */
	public int numBuyingOrders() {
		return buyingOrders.size();
	}
	
	/**
	 * Returns the current buying price in the market.
	 * @return Returns the head price of the sellingOrders queue.
	 */
	public double getMarketBuyPrice() {
		if (sellingOrders==null)
			return 0;
		if (sellingOrders.size()==0)
			return 0;
		return sellingOrders.peek().price;
	}
	/**
	 * Returns the current selling price in the market.
	 * @return Returns the head price of the buyingOrders queue.
	 */
	public double getMarketSellPrice() {
		if (buyingOrders==null)
			return 0;
		if (buyingOrders.size()==0)
			return 0;
		return buyingOrders.peek().price;
	}
	
	/**
	 * Returns the total market volume of the market in dollars.
	 * @return The total market volume of the market in dollars.
	 */
	public double getTotUSD() {
		double totUSD=0;
		for (BuyingOrder buyo : buyingOrders) {
			totUSD += (buyo.amount* buyo.price);
		}
		return totUSD;
	}
	/**
	 * Returns the total market volume of the market in PQoins.
	 * @return The total market volume of the market in PQoins.
	 */
	public double getTotPQ() {
		double totPQ=0;
		
		for (SellingOrder sello : sellingOrders) {
			totPQ += sello.amount;
		}
		return totPQ;
	}
	
	/**
	 * Returns the number of successful transactions done to this point.
	 * @return Number of successful transactions done to this point.
	 */
	public int numSuccessfullTransactions() {
		if (this.transactions ==null)
			return 0;
		return transactions.size();
	}
	
	/**
	 * Calculates and returns the average price of the market.
	 * @return The average price of the market.
	 */
	public double getAvgPrice() {
		if(sellingOrders==null || sellingOrders.size()==0)
			if(buyingOrders==null || buyingOrders.size()==0)
				return 0;
		
		if(sellingOrders==null || sellingOrders.size()==0)
			return (buyingOrders.peek().price);
		
		if(buyingOrders==null || buyingOrders.size()==0)
			return (sellingOrders.peek().price);
		
		return (buyingOrders.peek().price + sellingOrders.peek().price) / 2;
		
		
	}


}
