package com.abc.account;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super();
		label = "Checking Account\n";
	}

	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		return (amount * .001) ;
	}

}
