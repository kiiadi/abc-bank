package com.abc.transaction;

import com.abc.util.DateProvider;


/**
 * 
 * @author Sanju Thomas
 *
 */
public class Deposit extends Transaction implements ITransaction{

	private double amount;
	
	public Deposit(final double amount){
		super.validate(amount);
		this.amount = amount;
		super.date = DateProvider.getInstance().now();
	}

	public double getAmount() {
		return amount;
	}

}
