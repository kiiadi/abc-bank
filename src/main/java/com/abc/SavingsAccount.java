package com.abc;

public class SavingsAccount extends Account {

	public SavingsAccount() {
		super();
	}

	@Override
	public double interestEarned(double amount) {

		if (amount <= 1000) {
			return 1.0* amount * 0.001/365.0;
		} else {
			return 1000.0*0.001/365.0 + (amount-1000.0) * 0.002/365.0;
		}
	}

	@Override
	public String printName() {
		  return "Savings Account\n";
      }
}
