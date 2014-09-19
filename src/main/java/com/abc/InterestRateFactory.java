package com.abc;

import java.util.HashMap;
import java.util.Map;

public class InterestRateFactory {

	private static final Map<AccountType, iInterestRate> interestTypeMap;
	static {
		interestTypeMap = new HashMap<AccountType, iInterestRate>();
		interestTypeMap.put(AccountType.CHECKING, new CheckingRate());
		interestTypeMap.put(AccountType.MAXI_SAVINGS, new MaxiSavingsRate());
		interestTypeMap.put(AccountType.SAVINGS, new SavingsRate());
	}

	public iInterestRate rateCalculator(AccountType accountType) {

		return interestTypeMap.get(accountType);

	}

}
