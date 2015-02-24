package com.abc.banking.model.account;

import com.abc.banking.exception.BankingCriticalException;

/**
 * Checking Account
 *
 */
public class CheckingAccount extends AbstractAccount {
	
	private static final double INTEREST_RATE = 0.001 ;

	@Override
	public double calculateInterest() throws BankingCriticalException {
		
		return accountBalance * INTEREST_RATE ;
	}
	
	@Override 
	public String toString() {		
		return AccountType.CHECKING.name();
	}

}
