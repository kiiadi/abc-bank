package com.abc.account;

public class MaxiSavingsAccount extends AbstractAccount {

	public MaxiSavingsAccount(AccountType accountType) {
		super(accountType);
	}

	@Override
	public double interestEarned() {
		double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;
	}

}
