package com.abc;

import com.abc.BankConstants.AccountType;

public class InterestCalculatorFactory {
	public static InterestCalculator getInterestCalculator(
			AccountType accountType) {
		switch (accountType) {
		case SAVINGS:
			return new SavingInterestCalculator();
		case CHECKING:
			return new CheckingInterestCalculator();
		case MAXI_SAVINGS:
			return new MaxiSavingInterestCalculator();
		default:
			throw new IllegalArgumentException("Invalid account type ."
					+ accountType);
		}
	}
}
