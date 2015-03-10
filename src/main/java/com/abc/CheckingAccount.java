package com.abc;

import java.math.BigDecimal;

/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */


public class CheckingAccount extends Account {

	@Override
	public String getAccountDescription() {
		return "Checking Account";
	}

	@Override
	protected BigDecimal calculateInterest(BigDecimal amount) {
		return  amount.multiply( BigDecimal.valueOf( 0.001) );
	}

}
