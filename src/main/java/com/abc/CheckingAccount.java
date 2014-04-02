package com.abc;

public class CheckingAccount extends Account {
	
	@Override
	protected double interestEarned(double amount, int days) {
		return (amount * 0.001) * (double) days / 365.0;
	}

	@Override
	public String getDescription() {
		return "Checking Account";
	}
}
