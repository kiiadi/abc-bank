package com.abc;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

	private static BigDecimal ONETHOUSAND = new BigDecimal("1000");
	private static BigDecimal POINTONEPERCENT = new BigDecimal("0.001");
	private static BigDecimal POINTTWOPERCENT = new BigDecimal("0.002");
	private static BigDecimal ONE = new BigDecimal("1");
	
	public SavingsAccount(BigDecimal startingBalance) {
		super(startingBalance);
	}

	
	public BigDecimal interestEarned() {
		BigDecimal amount = sumTransactions();
		if (amount.compareTo(ONETHOUSAND) == 0 || amount.compareTo(ONETHOUSAND) == -1)
			return amount.multiply(POINTONEPERCENT);
		else
			return ONE.add((amount.subtract(ONETHOUSAND)).multiply(POINTTWOPERCENT));
	}
	
	
}
