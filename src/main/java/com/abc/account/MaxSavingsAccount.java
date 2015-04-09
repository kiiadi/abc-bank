package com.abc.account;

import java.util.Date;

import com.abc.calculator.InterestCalculator;
import com.abc.util.Utils;

public class MaxSavingsAccount extends Account {

	public MaxSavingsAccount() {
		super();
	}

	public MaxSavingsAccount(String accountNumber) {
		super(accountNumber);
	}

	@Override
	public double getInterestEarned() {
		Date endDate = getEndDateForInterestCalc();
		if(getLastWithdrawalDate() == null || Utils.getDaysBetween(getLastWithdrawalDate(), endDate) > 10 ) {
			return InterestCalculator.calculate(getAccountBalance().doubleValue(), 5, getOpeningDate(), endDate);
		}else {
			return InterestCalculator.calculate(getAccountBalance().doubleValue(), 0.01, getOpeningDate(), endDate);
		}
	}

	@Override
	public String getAccountType() {
		return AccountType.MAX_SAVINGS.getDisplayName();
	}

}
