package elements;


/**
 * Class for the orders to be placed in the market. Parent class of sellingOrder and buyingOrder classes.
 * @author Hakan
 *
 */
public class Order {
	
	
	double amount;
	double price;
	
	int traderID;
	/**
	 * The constructor for the orders.
	 * @param traderID The ID of trader putting the order.
	 * @param amount The amount of PQ in the order.
	 * @param price The price of each PQ in the order.
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
		
		
	}
	
	
	
}

