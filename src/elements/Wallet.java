package elements;

public class Wallet {

	
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	/**
	 * Available dollars are the dollars that are not blocked in the wallet.
	 */
	private double availableDollars;
	/**
	 * Available coins are the dollars that are not blocked in the wallet.
	 */
	private double availableCoins;
	/**
	 * Constructor for wallet of each trader.
	 * @param dollars The amount of dollars to be put in the wallet.
	 * @param coins The amount of coins to be put in the wallet.
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		this.availableDollars = dollars;
		this.availableCoins = coins;
	}

	/**
	 * Returns the amount of dollars in the wallet.
	 * @return The total amount of dollars in the wallet.
	 */
	public double getDollars() {
		return dollars;
	}

	/**Sets the amount of dollars in the wallet.
	 * @param dollars amount of dollars to set
	 */
	public void setDollars(double dollars) {
		this.dollars = dollars;
		availableDollars = this.dollars - blockedDollars;
	}

	/**
	 * Returns the amount of coins in the wallet.
	 * @return the amount of coins in the wallet
	 */
	public double getCoins() {
		return coins;
	}

	/**
	 * Sets the amount of coins in the wallet.
	 * @param coins the amount of coins to set
	 */
	public void setCoins(double coins) {
		this.coins = coins;
		availableCoins = this.coins - blockedCoins;
	}

	/**
	 * Returns the amount of blocked dollars in the wallet.
	 * @return The amount of blocked dollars in the wallet.
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}

	/**
	 * Sets the amount of blocked dollars in the wallet.
	 * @param blockedDollars The amount of blockedDollars to set.
	 */
	public void setBlockedDollars(double blockedDollars) {
		this.blockedDollars = blockedDollars;
		availableDollars = this.dollars - blockedDollars;
	}

	/**
	 * Returns the amount of blocked coins in the wallet.
	 * @return The amount of blocked coins in the wallet.
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}

	/**
	 * Sets the amount of blocked coins in the wallet.
	 * @param blockedCoins The amount of blocked coins to set.
	 */
	public void setBlockedCoins(double blockedCoins) {
		this.blockedCoins = blockedCoins;
		availableCoins = this.coins - blockedCoins;
	}

	/**
	 * Returns the amount of available dollars, which are the dollars that are not blocked in the wallet.
	 * @return the amount of available dollars, which are the dollars that are not blocked in the wallet.
	 */
	public double getAvailableDollars() {
		return availableDollars;
	}

	/**
	 * Returns the amount of available coins, which are the coins that are not blocked in the wallet.
	 * @return The amount of available coins, which are the coins that are not blocked in the wallet.
	 */
	public double getAvailableCoins() {
		return availableCoins;
	}
}
