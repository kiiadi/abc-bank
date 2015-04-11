package com.abc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CheckingAccount extends Account{

	private static final BigDecimal INTREST_RATE = new BigDecimal("0.001");
	
	public CheckingAccount(String accountNumber) {
		super(Account.CHECKING, accountNumber);
		// TODO Auto-generated constructor stub
	}

    public BigDecimal interestEarned() {
    	BigDecimal amount = sumTransactions();
        return amount.multiply(INTREST_RATE).divide(NUM_OF_DAYS_YEAR,  MathContext.DECIMAL128);
    }
}
