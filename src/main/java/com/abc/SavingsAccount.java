package com.abc;

import java.math.BigDecimal;

/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */


public class SavingsAccount extends Account {

	@Override
	public String getAccountDescription() {
		return "Savings Account";
	}


	@Override
	protected BigDecimal calculateInterest(BigDecimal amount) {
		if (amount.intValue() <= 1000)
			return amount.multiply( BigDecimal.valueOf(0.001) );
		return BigDecimal.valueOf(1).add( amount.subtract( BigDecimal.valueOf(1000)).multiply(BigDecimal.valueOf(0.002) ));
		//            case SUPER_SAVINGS:
			//                if (amount <= 4000)
		//                    return 20;

	}

}
