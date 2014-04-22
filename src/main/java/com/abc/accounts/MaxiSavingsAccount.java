/**
 * 
 */
package com.abc.accounts;

import java.util.Date;

import com.abc.utils.DateProvider;

/**
 * Contains specific attributes and behaviors of a maxi-savings account type.
 *
 */
public class MaxiSavingsAccount extends Account {
private static final int TEN_DAYS = 10;
	
	public MaxiSavingsAccount() {
		super(AccountType.MAXI_SAVINGS_ACCOUNT);
	}

	/* 
	 * Changed Maxi-Savings accounts to have an interest rate of 5% assuming no withdrawals
	 * in the past 10 days otherwise 0.1%
	 */
	@Override
	public double interestEarned() {
		double amount = getBalance();
		
		if(hasWithdrawalsInThePastDays(TEN_DAYS)) {
			return amount * 0.001;
		}
		else {
			return amount * 0.05;
		}
	}
	
	private boolean hasWithdrawalsInThePastDays(int numPastDays) {
		// add 1 day since we're using the "after()" method
		Date pastDate = DateProvider.getInstance().getDate(-(numPastDays + 1));
		
		for(Transaction transaction : getTransactions()) {
			if(transaction.getAmount() < 0 && 
					transaction.getTransactionDate().after(pastDate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return accountType.getName();
	}

}
