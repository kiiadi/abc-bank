package com.abc;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
	
	private static BigDecimal POINTONEPERCENT = new BigDecimal("0.001");
	public CheckingAccount(BigDecimal startingBalance) {
		super(startingBalance);
	}
	
    public synchronized BigDecimal interestEarned() {
    	BigDecimal amount = sumTransactions();
        return amount.multiply(POINTONEPERCENT);
    }
	
}
