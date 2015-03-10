package com.abc;

import java.math.BigDecimal;
import java.util.Calendar;

/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */


public class MaxiSavingsAccount extends Account {
	

	private long lastWithDrawal = 0;

	@Override
	public String getAccountDescription() {
		return "Maxi Savings Account";
	}

	@Override
	protected BigDecimal calculateInterest(BigDecimal amount) {
		
		double maxInterestRate = isLongerThanDays(10 ) ? 0.05 : 0.01;
		
		if (amount.intValue() <= 1000)
			return amount.multiply(BigDecimal.valueOf( 0.02 ));
		if (amount.intValue() <= 2000) {
			;
			return BigDecimal.valueOf(20).add( (amount.subtract(BigDecimal.valueOf(1000) ).multiply( BigDecimal.valueOf(maxInterestRate) ))) ;
		}
		return BigDecimal.valueOf(70).add( ( amount.subtract(BigDecimal.valueOf(2000)) ).multiply( BigDecimal.valueOf(0.1) ));
	}

	@Override
	public void withdraw(double amount) {
		super.withdraw(amount);
		lastWithDrawal  = System.currentTimeMillis();
	}
	
	private boolean isLongerThanDays( int days ){
		if ( lastWithDrawal == 0 ) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(lastWithDrawal);
		cal.add(Calendar.DAY_OF_YEAR, days);
		
		return (System.currentTimeMillis() > cal.getTimeInMillis() ) ? true: false;
	}

}
