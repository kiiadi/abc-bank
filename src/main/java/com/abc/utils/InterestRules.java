package com.abc.utils;

public class InterestRules {
	
	public static double getInterestOnCheckingAccount(double amount) {
		return amount * 0.01;
	}
	
	public static double getInterestOnSavingsAccount(double amount) {
		if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;
	}
	
	/*
	public static double getInterestOnSuperSavingsAccount(double amount) {
		if (amount <= 4000) 
			return 20; 
	}
	*/
	
	public static double getInterestOnMaxiSavingsAccount(double amount, boolean useSpecialIntrestRate) {
		if(useSpecialIntrestRate) {
			return amount * 0.05;
		} else {
			return amount * 0.001;
		}
		/* Original rule
		{	
			if (amount <= 1000)
				return amount * 0.02;
			else if (amount <= 2000)
				return 20 + (amount-1000) * 0.05;
			else 
				return 70 + (amount-2000) * 0.1;
		}
		*/
	}
	
	public static double getDefaultInterest(double amount) {
		return amount * 0.001;
	}
}
