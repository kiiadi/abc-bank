package com.abc;

public class AccountChecking extends Account {
	public double interestEarnedIn(long days, double onAmount) {
		return onAmount * days / DAYS_IN_YEAR * DEFAULT_RATE;
	}

	public String statementHeading() {
		return "Checking Account";
	}
}
