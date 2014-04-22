package com.abc.account;

import static com.abc.util.InterestRateCalculator.dailyCompoundedInterest;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

	/**
	 * Assuming min balance of savings account is $1000.
	 */
	private static final BigDecimal MIN_BALANCE = new BigDecimal(1000);
	private static final double rate_1 = 0.001d;
	private static final double rate_2 = 0.002d;
	
	@Override
	public AccountType getType() {
		return AccountType.SAVINGS;
	}

	@Override
	public BigDecimal getMinBalance() {
		return MIN_BALANCE;
	}

	@Override
	public BigDecimal interestEarned() {
		BigDecimal sum = sumTransactions();
		if(sum.compareTo(new BigDecimal(1000)) <= 0) {
			return dailyCompoundedInterest(sum, rate_1);
		}else {
			BigDecimal sumLess1000 = sum.subtract(new BigDecimal(1000));
			BigDecimal interest1 = dailyCompoundedInterest(new BigDecimal(1000), rate_1);
			BigDecimal interest2 = dailyCompoundedInterest(sumLess1000, rate_2);
			return interest1.add(interest2);
		}
	}

}
