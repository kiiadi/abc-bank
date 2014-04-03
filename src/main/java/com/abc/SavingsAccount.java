package com.abc;

public class SavingsAccount extends Account {
	
	@Override
	protected double interestEarned(double amount, int days) {
        if (amount <= 1000)
            return amount * 0.001 * (double) days / 365.0;
        else
            return (1 + (amount-1000) * 0.002) * (double) days / 365.0;
	}

	@Override
	public String getDescription() {
		return "Savings Account";
	}

}
