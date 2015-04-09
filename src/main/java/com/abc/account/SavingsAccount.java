package com.abc.account;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.calculator.InterestCalculator;

public class SavingsAccount extends Account {

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(String accountNumber) {
		super(accountNumber);
	}

	@Override
	public double getInterestEarned() {
		Date endDate = getEndDateForInterestCalc();
		if(getAccountBalance().doubleValue() <= 1000.00){
			return InterestCalculator.calculate(getAccountBalance().doubleValue(), 0.01, getOpeningDate(), endDate);
		}else {
			BigDecimal balanceOver1000 = getAccountBalance().subtract(new BigDecimal(1000.00));
			
			return 
					InterestCalculator.calculate(1000.00, 0.01, getOpeningDate(), endDate)
					+
					InterestCalculator.calculate(balanceOver1000.doubleValue(), 0.02, getOpeningDate(), endDate);
		}
	}

	@Override
	public String getAccountType() {
		return AccountType.SAVINGS.getDisplayName();
	}

}
