package com.abc;

import java.util.Date;

public class SavingsAccount extends Account {
	private static double DAILY_RATE_TIER1 = 0.001 / 365.0;
	private static double DAILY_RATE_TIER2 = 0.002 / 365.0;
	private static double TIER1_THRESHOLD_AMOUNT = 1000.0;

	public String getTypeName() {
		return AccountType.SAVINGS.toString();
	}

	/**
	 * Calculate one day's worth of interest, according to the following specification:
	 * Savings accounts have a rate of RATE1 for the first $1,000 then RATE2
	 *
	 * @param balance base balance for daily interest calculation
	 * @return one day's interest amount
	 */
	public double calculateDailyInterest(double balance, Date asOfDate) {
		double interest;

		if (balance <= TIER1_THRESHOLD_AMOUNT) {
			interest = balance * DAILY_RATE_TIER1;
		} else {
			interest = TIER1_THRESHOLD_AMOUNT * DAILY_RATE_TIER1;
			interest += (balance - TIER1_THRESHOLD_AMOUNT) * DAILY_RATE_TIER2;
		}

		return interest;
	}
}
