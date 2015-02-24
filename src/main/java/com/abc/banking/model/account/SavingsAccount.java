package com.abc.banking.model.account;

import com.abc.banking.exception.BankingCriticalException;

/**
 * Savings Account
 *
 */
public class SavingsAccount extends AbstractAccount {

	private static final double INTEREST_RATE_BELOW_MIN_LIMIT = 0.001;
	private static final double INTEREST_RATE_ABOVE_MIN_LIMIT = 0.002;
	private static final double MINIMUM_LIMIT = 1000.0;
	
	@Override
	public double calculateInterest() throws BankingCriticalException {

		if (accountBalance > MINIMUM_LIMIT) {

			return ((accountBalance - MINIMUM_LIMIT) * INTEREST_RATE_ABOVE_MIN_LIMIT 
					+ MINIMUM_LIMIT	* INTEREST_RATE_BELOW_MIN_LIMIT);
		} else {
			return (accountBalance * INTEREST_RATE_BELOW_MIN_LIMIT);
		}

	}

	@Override 
	public String toString() {	
		
		return AccountType.SAVINGS.name();
	}
}
