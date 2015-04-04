package com.abc;


public class SavingInterestCalculator implements InterestCalculator {
	public double calculate(Account account) {
		if (account.getAmount() <= 1000)
			return account.getAmount() * 0.001 / BankConstants.DAYS_IN_A_YEAR;
		else
			return ( 1 + (account.getAmount() - 1000) * 0.002 ) / BankConstants.DAYS_IN_A_YEAR; 
	}

}
