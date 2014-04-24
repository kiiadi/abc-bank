package com.abc.account;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

	public SavingsAccount() {
		super();
		label = "Savings Account\n";
	}

	/*
	 * savings accounts have a rate of 0.1% for the first $1,000 then 0.2%
	 */
	@Override
	public BigDecimal interestEarned() {
		BigDecimal amount = sumTransactions();
		if (amount.compareTo(new BigDecimal("1000")) <= 0) {
			return amount.multiply(new BigDecimal("0.001"));
		} else
			return new BigDecimal("1").add((amount.subtract(new BigDecimal(
					"1000")).multiply(new BigDecimal("0.002"))));
	}

}
