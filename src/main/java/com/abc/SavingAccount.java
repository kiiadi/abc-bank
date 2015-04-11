package com.abc;

import java.math.BigDecimal;
import java.math.MathContext;

public class SavingAccount extends Account {
	private static final BigDecimal INTREST_RATE_FOR_THOUSAND = new BigDecimal("0.001");
	private static final BigDecimal INTREST_RATE_AFTER_THOUSAND = new BigDecimal("0.002");
	private static final BigDecimal ONE_THOUSAND = new BigDecimal("1000");

	public SavingAccount(String accountNumber) {
		super(Account.SAVINGS, accountNumber);
		// TODO Auto-generated constructor stub
	}


    public BigDecimal interestEarned() {
    	BigDecimal amount = sumTransactions();

        if (amount.compareTo(ONE_THOUSAND) <= 0){
            return amount.multiply(INTREST_RATE_FOR_THOUSAND).divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);
        }
        else{
        	amount = amount.subtract(ONE_THOUSAND);
        	amount = amount.multiply(INTREST_RATE_AFTER_THOUSAND);
        	amount = amount.add(new BigDecimal("1"));
        	return amount.divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);
        	
            //return 1 + (amount-1000) * 0.002;
        }
    }
}
