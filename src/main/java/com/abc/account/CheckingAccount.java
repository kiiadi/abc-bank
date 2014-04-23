package com.abc.account;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super();
		label = "Checking Account\n";
	}

	/*
	 * checking accounts have a flat rate of 0.1%
	 */
	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		return (amount * .001) ;
	}

}
