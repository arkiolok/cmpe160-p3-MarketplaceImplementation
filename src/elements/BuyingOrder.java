package elements;

/**
 * Class of buying orders. Which is sub-class of order.
 * @author Hakan
 *	
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {

	/**
	 * The constructor for the buying order.
	 * @param traderID The ID of the trader creating the order.
	 * @param amount The amount of coins to buy.
	 * @param price The price of each coin to buy.
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
		
	}

	@Override
	public int compareTo(BuyingOrder o) {
		if(this.price>o.price) {
			return -1;
		} else if (this.price == o.price) {
			if(this.amount>o.amount) {
				return -1;
			} else if (this.amount == o.amount) {
				if(this.traderID>o.traderID) {
					return 1;
				} else {
					return -1;
				}
				
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

}
