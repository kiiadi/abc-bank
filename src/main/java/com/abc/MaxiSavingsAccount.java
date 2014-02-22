package com.abc;

import java.util.Date;

public class MaxiSavingsAccount extends Account {

	protected static double MAXI_SAVINGS_DAILY_RATE_DEFAULT = 0.001 / 365.0;
	protected static double MAXI_SAVINGS_DAILY_RATE_NO_WITHDRAWALS = 0.05 / 365.0;
	protected static int MINIMUM_NO_WITHDRAWAL_DAYS = 10;

	public String getTypeName() {
		return AccountType.MAXI_SAVINGS.toString();
	}

	/**
	 * Calculate one day's worth of interest using the following specification:
	 * Interest rate of RATE1 assuming no withdrawals in the past N days otherwise RATE2
	 *
	 * @param balance base balance for daily interest calculation
	 * @return one day's interest amount
	 */
	public double calculateDailyInterest(double balance, Date asOfDate) {
		double dailyRate = (hadRecentWithdrawals(asOfDate, MINIMUM_NO_WITHDRAWAL_DAYS)) ?
				MAXI_SAVINGS_DAILY_RATE_DEFAULT : MAXI_SAVINGS_DAILY_RATE_NO_WITHDRAWALS;

		return balance * dailyRate;
	}

}
