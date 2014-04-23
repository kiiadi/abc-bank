package com.abc.account;

public class SavingsAccount extends Account {

	public SavingsAccount() {
		super();
		label = "Savings Account\n";
	}

	/* 
	 * savings accounts have a rate of 0.1% for the first $1,000 then 0.2%
	 */
	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		if (amount <= 1000) {
			return amount * 0.001;
		} else
			return (1 + (amount - 1000) * 0.002);
	}

}
