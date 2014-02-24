package com.abc;

public class MaxSavingsInterestStrategyImpl implements IInterestStrategy {

	public double calculateInterest(double amount) {
		if (amount <= ONE_THOUSAND)
			return amount * TWO_PERCENT;
		if (amount <= TWO_THOUSAND)
			return TWENTY + (amount - ONE_THOUSAND) * FIVE_PERCENT;
		return SEVENTY + (amount - TWO_THOUSAND) * TEN_PERCENT;
	}

}
