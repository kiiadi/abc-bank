package com.abc;

public class MaxiSavingsAccount extends Account {
	
	@Override
	protected double interestEarned(double amount, int days) {
	    if (amount <= 1000)
	        return (amount * 0.02) * (double) days / 365.0;
	    if (amount <= 2000)
	        return (20 + (amount-1000) * 0.05) * (double) days / 365.0;
	    return (70 + (amount-2000) * 0.1) * (double) days / 365.0;
	}

	@Override
	public String getDescription() {
		return "Maxi Savings Account";
	}
}
