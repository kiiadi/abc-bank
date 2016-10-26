package com.abc;

public class AccountSavings extends Account {
	private static final double RATE = 0.002;

	public double interestEarnedIn(long days, double onAmount) {
		double daysFrac = (double) days / DAYS_IN_YEAR;
		if (onAmount <= 1000)
			return (onAmount * daysFrac) * DEFAULT_RATE;
		else
			return (1000.0d * daysFrac) * DEFAULT_RATE + ((onAmount - 1000) * daysFrac) * RATE;
	}

	public String statementHeading() {
		return "Savings Account";
	}
}
