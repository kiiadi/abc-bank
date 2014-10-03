package com.abc;

/**
 * This class represents transfer transaction.
 * @author qyang28
 *
 */
public class Transfer extends Transaction {

	public Transfer(double amount) {
		super(amount);
	}
	
	@Override
	public String print() {
		if (amount >= 0) {
			return "  " + "transfer in"  + " " + Utils.toDollars(amount) + "\n";
		} else {
			return "  " + "transfer out"  + " " + Utils.toDollars(amount) + "\n";
		}
	}
}
