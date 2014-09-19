package com.abc;

public class SavingsRate implements iInterestRate {

	public double calculateEarned(double amount) {
		if (amount <= 1000)
			return amount * 0.001;
		else
			return 1 + (amount - 1000) * 0.002;
	}

}
