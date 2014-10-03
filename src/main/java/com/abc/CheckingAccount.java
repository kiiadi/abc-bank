package com.abc;

public class CheckingAccount extends Account {

	@Override
	public double interestEarned(double amount) {
		return amount * 0.001 / DAYS_IN_YEAR;
	}

	@Override
	public String printName() {
		return "Checking Account\n";
	}
}
