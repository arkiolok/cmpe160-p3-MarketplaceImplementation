package executable;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import elements.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Main method for the I/O operations.
 * @author Hakan
 *
 */
public class Main {
	public static Random myRandom;
	public static int tID;
	public static ArrayList<Trader> traders;
	
	public static void main(String[] args) throws FileNotFoundException {		
		
		Scanner in = new Scanner(new File(args[0])); // args[0] is the input file
		PrintStream out = new PrintStream(new File(args[1])); //args[1] is the output file

		int seed = (int) in.nextDouble();
		int initialFee = (int) in.nextDouble();
		Trader.numberOfUsers = (int) in.nextDouble();
		int numUsers = Trader.numberOfUsers;
		int numQueries = (int) in.nextDouble();
		in.nextLine();
		traders = new ArrayList<Trader>();
		tID =0;
		int invalidCounter=0;
		
		Market markt = new Market(initialFee);
		
		myRandom = new Random(seed);
		
		for (int i =0; i<numUsers; i++) {
			traders.add(new Trader(in.nextDouble(), in.nextDouble()));
			tID++;
		}
		
		for (int i=0;in.hasNextLine();i++) { 
			int currentOperation = (int) in.nextDouble();

			switch (currentOperation) {
			
			case 10:
				int buyID = (int) in.nextDouble();
				double buyPrice = in.nextDouble();
				double buyAmount = in.nextDouble();
				
				invalidCounter += traders.get(buyID).buy(buyAmount, buyPrice, markt);
				
				break;
				
			case 11:
				int buyMarketID = (int) in.nextDouble();
				double buyMarketAmount = in.nextDouble();
				
				if(markt.numSellingOrders()==0) {
					invalidCounter ++;
					break;
				}
				
				invalidCounter += traders.get(buyMarketID).buy(buyMarketAmount, markt.getMarketBuyPrice(), markt);
				break;
				
			case 20:
				int sellID = (int) in.nextDouble();
				double sellPrice = in.nextDouble();
				double sellAmount = in.nextDouble();
				
				invalidCounter += traders.get(sellID).sell(sellAmount, sellPrice, markt);
				break;
			case 21:
				int sellMarketID = (int) in.nextDouble();
				double sellMarketAmount = in.nextDouble();
				
				if(markt.numBuyingOrders()==0) {
					invalidCounter ++;
					break;
				}
				
				invalidCounter += traders.get(sellMarketID).sell(sellMarketAmount, markt.getMarketSellPrice(), markt);
				break;
			case 3:
				int depositID = (int) in.nextDouble();
				double depositAmount = in.nextDouble();
				
				traders.get(depositID).deposit(depositAmount);
				break;
			case 4:
				int withdrawID = (int) in.nextDouble();
				double withdrawAmount = in.nextDouble();
				
				
				invalidCounter += traders.get(withdrawID).withdraw(withdrawAmount);
				break;
				
			case 5:
				int printID = (int) in.nextDouble();
				
				out.printf("Trader %d: %.5f$ %.5fPQ\n", printID, traders.get(printID).getDollars(), traders.get(printID).getCoins());
				break;
				
			case 777:
				for (Trader tr: traders) {
					tr.depositCoins(myRandom.nextDouble()*10);
				}
				break;
			
			case 666:
				double openPrice = in.nextDouble();
				markt.makeOpenMarketOperation(openPrice);
				break;
				
			case 500:	
				out.printf("Current market size: %.5f %.5f\n", markt.getTotUSD(), markt.getTotPQ());
				break;
			
			case 501:
				out.printf("Number of successful transactions: %d\n", markt.numSuccessfullTransactions());
				break;
				
			case 502:
				out.printf("Number of invalid queries: %d\n", invalidCounter);
				break;
			case 505:
				out.printf("Current prices: %.5f %.5f %.5f\n",  markt.getMarketSellPrice(), markt.getMarketBuyPrice(), markt.getAvgPrice());
				break;
			case 555:
				for (Trader t : traders) {
					out.printf("Trader %d: %.5f$ %.5fPQ\n" , t.getID(), t.getDollars(), t.getCoins());	
				}
				break;
			}
		}
		
		in.close();
		out.close();
	}

}
