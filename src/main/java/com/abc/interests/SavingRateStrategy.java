package com.abc.interests;

public class SavingRateStrategy implements InterestStrategy {	
	
	public double calculateInterest(double balance, int days) {
		
        if (balance <= SAVING_THRESHOLD) {
            return (balance * FLAT_RATE) * (days / DAYS_IN_A_YEAR);
        }

        return (1 + ((balance - SAVING_THRESHOLD) * SAVING_RATE)) * (days / DAYS_IN_A_YEAR);
	}
}