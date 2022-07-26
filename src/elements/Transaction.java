package elements;


/**
 * Transaction is the object that holds the orders that a transaction is made between.
 * @author Hakan
 *
 */
public class Transaction {

	
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	/**
	 * Constructor for the transaction.
	 * @param sellingOrder The relevant selling order.
	 * @param buyingOrder The relevant buying order.
	 */
	public Transaction (SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
		
		
	}

}
