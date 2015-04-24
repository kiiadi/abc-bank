package com.abc.account;

public class CheckingAccount extends AbstractAccount {

	public CheckingAccount(AccountType accountType) {
		super(accountType);
	}

	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		return amount * 0.001;
	}

}
