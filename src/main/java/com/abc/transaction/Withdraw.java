package com.abc.transaction;

import com.abc.util.DateProvider;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Withdraw extends Transaction implements ITransaction{
	
	private double amount;
	
	public Withdraw(final double amount){
		this.amount = amount;
		super.date = DateProvider.getInstance().now();
	}

	public double getAmount() {
		return amount;
	}

}
