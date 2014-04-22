package com.abc.account;

import static com.abc.util.InterestRateCalculator.dailyCompoundedInterest;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
	

	/**
	 * Assuming min balance of checking account is $100.
	 */
	private static final BigDecimal MIN_BALANCE = new BigDecimal(100);
	private static final double rate = 0.001d;
	
	@Override
	public AccountType getType() {
		return AccountType.CHECKING;
	}
	
	@Override
	public BigDecimal getMinBalance() {
		return MIN_BALANCE;
	}

	@Override
	public BigDecimal interestEarned() {
		BigDecimal sum = sumTransactions();
		return dailyCompoundedInterest(sum, rate);
	}

}
