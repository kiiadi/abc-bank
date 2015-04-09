package com.abc.account;

import com.abc.calculator.InterestCalculator;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super();
	}

	public CheckingAccount(String accountNumber) {
		super(accountNumber);
	}

	@Override
	public double getInterestEarned() {
		return InterestCalculator.calculate(getAccountBalance().doubleValue(), 0.01, getOpeningDate(), getEndDateForInterestCalc());
	}

	@Override
	public String getAccountType() {
		return AccountType.CHECKING.getDisplayName();
	}
}
