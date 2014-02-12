package com.abc.util;

import com.abc.model.impl.CheckingAccountImpl;
import com.abc.model.impl.MaxiSavingsAccountImpl;
import com.abc.model.impl.SavingsAccountImpl;

public class InterestCalculator {

	public double calculateInterest(final SavingsAccountImpl acct, double amount) {
		final double interest = 0.001;
		final double nextInterest = 0.002;
		 double newAmount = 0;
		
		if(amount > 1000) {
			final double otherAmount = ((amount - 1000) * nextInterest) + (amount - 1000);
			final double firstAmount = (1000 * interest) + 1000;
			newAmount = otherAmount + firstAmount;
			
		} else {
			newAmount = (amount * interest) + amount;
		}
		
		return newAmount;
	}

	public double calculateInterest(final MaxiSavingsAccountImpl acct, double amount) {
		final double interest = 0.02;
		final double nextInterest = 0.05;
		final double lastInterest = 0.10;
		 double newAmount = 0;
			
		if(amount <= 1000) {
			newAmount = (amount * interest) + amount;
			
		} else if (amount > 1000 && amount <= 2000) {
			final double otherAmount = ((amount - 1000) * nextInterest) + (amount - 1000);
			final double firstAmount = (1000 * interest) + 1000;
			newAmount = otherAmount + firstAmount;
		} else {
			
			final double otherAmount = (1000 * nextInterest) + 1000;
			final double firstAmount = (1000 * interest) + 1000;
			final double threeAmount = ((amount - 2000) * lastInterest) + (amount - 2000);
			newAmount = otherAmount + firstAmount + threeAmount;
			
		}
		
		return newAmount;
	}
	
	public double calculateInterest(final CheckingAccountImpl acct, double amount) {
		final double interest = 0.001;
		final double interAmount = interest * amount;
		return amount + interAmount ;
	}
}
