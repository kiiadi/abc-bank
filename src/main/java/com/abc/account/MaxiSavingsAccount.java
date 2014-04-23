package com.abc.account;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.abc.Transaction;

public class MaxiSavingsAccount extends SavingsAccount {

	private int withdrawalRuleDays = 10;

	public MaxiSavingsAccount() {
		super();
		label = "Maxi Savings Account\n";
	}

	/*
	 * provides interest rate of 5% assuming no withdrawals in the past 10 days
	 * otherwise 0.1%
	 */
	@Override
	public BigDecimal interestEarned() {

		BigDecimal amount = sumTransactions();

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -withdrawalRuleDays);
		Date offset = cal.getTime();

		boolean withdrawalRuleBroken = false;

		for (Transaction transaction : getTransactions()) {
			if (transaction.getAmount().compareTo(new BigDecimal("0")) < 0) { // a
																				// withdrawal
				if (transaction.getTransactionDate().after(offset)) {
					withdrawalRuleBroken = true;
					break;
				}
			}
		}

		if (withdrawalRuleBroken) {
			return amount.multiply(new BigDecimal("0.001"));
		} else {
			return amount.multiply(new BigDecimal("0.05"));
		}

	}

	public void setWithdrawalRuleDays(int withdrawalRuleDays) {
		this.withdrawalRuleDays = withdrawalRuleDays;
	}

}
