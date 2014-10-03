package com.abc;

/**
 * This class represents deposit transaction.
 * @author qyang28
 */

public class Deposit extends Transaction {
	
	public Deposit(double amount) {
		super(amount);
	}

	@Override
	public String print() {
		return "  " + "deposit"  + " " + Utils.toDollars(amount) + "\n";
	}
}
