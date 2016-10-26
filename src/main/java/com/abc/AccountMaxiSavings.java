package com.abc;

public class AccountMaxiSavings extends Account {
	private static final double RATE1 = 0.02;
	private static final double RATE2 = 0.05;
	private static final double RATE3 = 0.10;

	public double interestEarnedIn(long days, double onAmount) {
		double daysFrac = (double) days / DAYS_IN_YEAR;
		if (onAmount <= 1000)
			return (onAmount * daysFrac) * RATE1;
		else if (onAmount <= 2000)
			return (1000.0d * daysFrac) * RATE1 + ((onAmount - 1000) * daysFrac) * RATE2;
		else
			return (1000.0d * daysFrac) * RATE1 + (1000.0d * daysFrac) * RATE2 + ((onAmount - 2000) * daysFrac) * RATE3;
	}

	public String statementHeading() {
		return "Maxi Savings Account";
	}
}
