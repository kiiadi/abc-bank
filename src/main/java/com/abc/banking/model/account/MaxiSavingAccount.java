package com.abc.banking.model.account;

import com.abc.banking.exception.BankingCriticalException;
import com.abc.banking.transaction.TransactionType;

/**
 * Maximum Savings Account
 *
 */
public class MaxiSavingAccount extends AbstractAccount {

	private static final double INTEREST_RATE_MAX = 0.05;
	private static final double INTEREST_RATE_MIN = 0.001;
	private static final int WITHDRAWL_AGE = 10 ;
	

	@Override
	public double calculateInterest() throws BankingCriticalException {
		
		int transactionAge = getLastTransactionAge(TransactionType.DEBIT);
		
		if ( transactionAge > WITHDRAWL_AGE || transactionAge == -1) {			 
			return accountBalance * INTEREST_RATE_MAX ;
		} else {
			return accountBalance * INTEREST_RATE_MIN;
		}
		
	}

	@Override
	public String toString() {
		return AccountType.MAXI_SAVINGS.name();
	}
}
