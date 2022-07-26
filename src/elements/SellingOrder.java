package elements;

/**
 * Class of selling orders. Which is sub-class of order.
 * @author Hakan
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{
	
	/**
	 * The constructor for the selling order.
	 * @param traderID The ID of the trader creating the order.
	 * @param amount The amount of coins to sell.
	 * @param price The price of each coin to sell.
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
		
	}
	

	@Override
	public int compareTo(SellingOrder o) {
		if(this.price>o.price) {
			return 1;
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
			return -1;
		}
	}

}
