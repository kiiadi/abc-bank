package com.abc.account;

import java.math.BigDecimal;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super();
		label = "Checking Account\n";
	}

	/*
	 * checking accounts have a flat rate of 0.1%
	 */
	@Override
	public BigDecimal interestEarned() {
		return sumTransactions().multiply(new BigDecimal(".001"));
	}

}
