package com.abc.interests;

public class MaxRateStrategy implements InterestStrategy {

	public double calculateInterest(double balance, int days) {
		
	    if (balance <= SAVING_THRESHOLD)
	        return (balance * SAVING_RATE) * (double) days / DAYS_IN_A_YEAR;
	    
	    if (balance <= 2000)
	        return (20 + (balance - SAVING_THRESHOLD) * PROMOTED_SAVING_RATE) * (double) days / DAYS_IN_A_YEAR;
	    
	    return (70 + (balance - MAX_SAVING_THRESHOLD) * MAX_SAVING_RATE) * (double) days / DAYS_IN_A_YEAR;
	}
}