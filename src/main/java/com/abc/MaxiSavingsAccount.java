package com.abc;

import java.math.BigDecimal;

public class MaxiSavingsAccount extends Account {

	private static BigDecimal POINTONEPERCENT = new BigDecimal("0.001");
	private static BigDecimal FIVEPERCENT = new BigDecimal("0.05");
	private static Boolean isEligible = false;

	
	public MaxiSavingsAccount(BigDecimal startingBalance) {
		super(startingBalance);
	}

	public MaxiSavingsAccount(BigDecimal startingBalance, int dayInterval) {
		super(startingBalance, dayInterval);
	}
	
	public BigDecimal interestEarned() {
		for (Transaction t : getTransactions()) {
			if(Utils.calcNumOfDaysBetweenTwoDates(t.getTransactionDate(), DateProvider.getInstance().now()) < 10
					&& t.getAmount().signum() == 1){
				isEligible = true;
				break;
			}
		}
		BigDecimal amount = sumTransactions();
		if(isEligible){
			return amount.multiply(POINTONEPERCENT);
		}else{
			return amount.multiply(FIVEPERCENT);
		}
		
		
		
	}
	
	
}
