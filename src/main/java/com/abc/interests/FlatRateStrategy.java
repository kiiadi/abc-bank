package com.abc.interests;

public class FlatRateStrategy implements InterestStrategy {

	public double calculateInterest(double balance, int days) {
		
		return (balance * FLAT_RATE) * (days / DAYS_IN_A_YEAR);
	}
}
