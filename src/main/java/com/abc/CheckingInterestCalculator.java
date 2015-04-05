package com.abc;

public class CheckingInterestCalculator implements InterestCalculator {
	public double calculate(Account account) {
		return account.getAmount() * 0.001 / BankConstants.DAYS_IN_A_YEAR;
	}
}
