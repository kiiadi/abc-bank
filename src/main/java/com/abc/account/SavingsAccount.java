package com.abc.account;

public class SavingsAccount extends AbstractAccount {

	public SavingsAccount(AccountType accountType) {
		super(accountType);
	}

	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		if (amount <= 1000) {
			return amount * 0.001;
		} else {
			return 1 + (amount - 1000) * 0.002;
		}
	}
}
