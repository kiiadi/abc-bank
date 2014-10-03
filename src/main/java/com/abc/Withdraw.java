package com.abc;

/**
 * Class represents a withdraw transaction.
 * @author qyang28
 *
 */
public class Withdraw extends Transaction {

	/**
	 * Construct an object of withdraw transaction. 
	 * @param amount to be taken out of the account.
	 */
	public Withdraw(double amount) {
		super(-amount);
	}
	
	@Override
	public String print() {
		return "  " + "withdrawal"  + " " + Utils.toDollars(amount) + "\n";
	}

}
