package elements;

import executable.*;
/**
 * Trader is the object that holds the information and does operations about each of the traders.
 * @author Hakan
 *
 */
public class Trader {
	
	public static int numberOfUsers = 0;
	private int id;
	private Wallet wallet;
	/**
	 * The constructor for the trader.
	 * @param dollars The starting amount of dollars of the trader.
	 * @param coins The starting amount of coins of the trader.
	 */
	public Trader(double dollars, double coins) {
		
		this.id = Main.tID;
		this.wallet = new Wallet(dollars, coins);
		
	}
	
	/**
	 * Method for checking if the order can be placed and if it can, placing the order and blocking the relevant amount of assets.
	 * @param amount The amount of coins to sell.
	 * @param price	The price of each coin to sell.
	 * @param market The market where the order is placed.
	 * @return Returns 1 if the order can not be placed, 0 if the order is placed.
	 */
	public int sell(double amount, double price, Market market) {
		//unsatisfied conditions
		if (amount > wallet.getAvailableCoins()) {
			return 1;
		}
		
		market.giveSellOrder(new SellingOrder(this.id, amount, price));
		wallet.setBlockedCoins(wallet.getBlockedCoins() + amount);
		return 0;
	}
	
	/**
	 * Method for checking if the order can be placed and if it can, placing the order and blocking the relevant amount of assets.
	 * @param amount The amount of coins to buy.
	 * @param price The price of each coin to buy.
	 * @param market The market where the order is placed.
	 * @return Returns 1 if the order can not be placed, 0 if the order is placed.
	 */
	public int buy(double amount, double price, Market market) {
		//unsatisfied contitions
		if(amount*price > this.wallet.getAvailableDollars()) {
			return 1;
		}
		
		market.giveBuyOrder(new BuyingOrder(this.id, amount, price));
		wallet.setBlockedDollars(wallet.getBlockedDollars() + (amount*price));
		
		
		return 0;
	}
	
	/**
	 * Deposits dollars to the wallet of the trader.
	 * @param amount The amount to deposit.
	 */
	public void deposit (double amount) {
		this.wallet.setDollars(this.wallet.getDollars() + amount);
		return;
	}
	
	/**
	 * Withdraws dollars from the wallet of the trader.
	 * @param amount The amount to withdraw.
	 * @return Returns 1 if the withdrawal can not be done, 0 if it can.
	 */
	public int withdraw (double amount) {
		if(amount> this.wallet.getAvailableDollars())
			return 1;
		
		this.wallet.setDollars(this.wallet.getDollars() - amount);		
		return 0;
	}
	
	/**
	 * Deposits coin to the wallet of the trader.
	 * @param amount The amount to deposit.
	 */
	public void depositCoins (double amount) {
		this.wallet.setCoins(this.wallet.getCoins() + amount);
		return;
	}
	
	/**
	 * Withdraws coin from the wallet of the trader.
	 * @param amount The amount to deposit.
	 * @return Returns 1 if the withdrawal can not be done, 0 if it can.
	 */
	public int withdrawCoins (double amount) {
		if (amount> this.wallet.getAvailableCoins())
			return 1;
		
		this.wallet.setCoins(this.wallet.getCoins() - amount);
		return 0;
	}
	
	/**
	 * Sets the amount of dollars in the wallet.
	 * @param amount The amount to set.
	 */
	public void setDollars(double amount) {
		this.wallet.setDollars(amount);
		return;
	}
	
	/**
	 * Sets the amount of coins in the wallet.
	 * @param amount The amount to set.
	 */
	public void setCoins(double amount) {
		this.wallet.setCoins(amount);
		return;
	}
	
	/**
	 * Returns the amount of dollars in the wallet.
	 * @return Returns the amount of dollars in the wallet.
	 */
	public double getDollars () {
		
		return wallet.getDollars();
	}
	/**
	 * Returns the amount of coins in the wallet.
	 * @return Returns the amount of coins in the wallet.
	 */
	public double getCoins () {
		
		return wallet.getCoins();
	}
	
	/**
	 * Unblocks dollars from the wallet of the trader.
	 * @param amount The amount to unblock.
	 */
	public void unblockDollars(double amount) {
		this.wallet.setBlockedDollars( this.wallet.getBlockedDollars() - amount);
	}
	
	/**
	 * Unblocks coins from the wallet of the trader.
	 * @param amount The amount to unblock.
	 */
	public void unblockCoins (double amount) {
		this.wallet.setBlockedCoins(this.wallet.getBlockedCoins() - amount);
	}
	
	/**
	 * Returns the id of the trader.
	 * @return Returns the id of the trader
	 */
	public int getID () {
		return this.id;
	}


}
