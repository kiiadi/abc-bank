package com.abc;

public class DefaultInterestStrategyImpl implements IInterestStrategy {

	public double calculateInterest(double amount) {
		return amount * POINT_ONE_PERCENT;
	}

}
