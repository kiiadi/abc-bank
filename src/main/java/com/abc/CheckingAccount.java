package com.abc;

public class CheckingAccount extends Account{

	public CheckingAccount(int accountType) {
		super(accountType);
	}

	public double interestEarned() {
		double amount = sumTransactions();
		return amount * 0.001;
	}
}
