package com.abc;

import java.util.Date;

public class CheckingAccount extends Account {

	private static final double DAILY_RATE = 0.1 / 100.0 / 365.0;

	public String getTypeName() {
		return AccountType.CHECKING.toString();
	}

	/**
	 * Calculate one day's worth of interest, using flat daily rate.
	 *
	 * @param balance base balance for daily interest calculation
	 * @return one day's interest amount
	 */
	public double calculateDailyInterest(double balance, Date asOfDate) {
		return balance * DAILY_RATE;
	}

}
