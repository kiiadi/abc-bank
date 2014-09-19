package com.abc;

public class CheckingRate implements iInterestRate {

	public double calculateEarned(double amount) {
		return amount * 0.001;
	}

}
