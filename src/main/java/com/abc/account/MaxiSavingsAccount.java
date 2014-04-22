package com.abc.account;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.abc.Transaction;

public class MaxiSavingsAccount extends SavingsAccount {

	public MaxiSavingsAccount() {
		super();
		label = "Maxi Savings Account\n";
	}

	/*
	 * provides interest rate of 5% assuming no withdrawals in the past 10 days
	 * otherwise 0.1%
	 */
	@Override
	public double interestEarned() {

		double amount = sumTransactions();

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -10);
		Date tenDaysAgo = cal.getTime();

		boolean withdrawalInThePast10Days = false;

		for (Transaction transaction : getTransactions()) {
			if (transaction.getAmount() < 0) { // a withdrawal
				if (transaction.getTransactionDate().after(tenDaysAgo)) {
					withdrawalInThePast10Days = true;
					break;
				}
			}
		}

		if (withdrawalInThePast10Days) {
			return amount * 0.001;
		} else {
			return amount * 0.05;
		}

	}

}
