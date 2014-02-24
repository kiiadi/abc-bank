package com.abc;

public class SavingsInterestStrategyImpl implements IInterestStrategy {	
	public double calculateInterest(double amount) {
			if (amount <= ONE_THOUSAND)
				return amount * POINT_ONE_PERCENT;
			else
				return 1 + (amount - ONE_THOUSAND) * POINT_TWO_PERCENT;
	}

}
