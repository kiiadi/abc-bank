package com.abc.interests;

import java.util.HashMap;
import java.util.Map;

import com.abc.accounts.AccountType;

public class InterestStrategyContext {
	
	private static final Map<AccountType, InterestStrategy> strategies = new HashMap<>();

	static {
		
		strategies.put(AccountType.CHECKING, new FlatRateStrategy());
		strategies.put(AccountType.SAVINGS, new SavingRateStrategy());
		strategies.put(AccountType.MAX_SAVINGS, new MaxRateStrategy());
	}

	public double calculateInterest(AccountType accountType, double balance, int days) {

		return strategies.get(accountType).calculateInterest(balance, days);
		
	}
}