package com.abc.account;

import static com.abc.util.InterestRateCalculator.dailyCompoundedInterest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class MaxiSavingsAccount extends Account {

	/**
	 * Assuming min balance of maxi-savings account is $1500.
	 */
	private static final BigDecimal MIN_BALANCE = new BigDecimal(1500);
	private static final double rate_1 = 0.001d;
	private static final double rate_2 = 0.05d;

	@Override
	public AccountType getType() {
		return AccountType.MAXI_SAVINGS;
	}

	@Override
	public BigDecimal getMinBalance() {
		return MIN_BALANCE;
	}

	@Override
	public BigDecimal interestEarned() {
		Date tenDaysAgo = daysFromNow(-10);
		boolean hasWithdrawalWithinLast10days = false;
		for (Transaction t : getTransactions()) {
			if (t.getType() == TransactionType.Withdrawal
					&& !(t.getTransactionDate().before(tenDaysAgo))) {
				hasWithdrawalWithinLast10days = true;
			}
		}
		BigDecimal sum = sumTransactions();
		return hasWithdrawalWithinLast10days ? dailyCompoundedInterest(sum,
				rate_1) : dailyCompoundedInterest(sum, rate_2);

	}

	private Date daysFromNow(int daysAgo) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, daysAgo);
		return cal.getTime();
	}

}
