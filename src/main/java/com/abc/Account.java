package com.abc;

import java.util.Date;
import java.util.TreeSet;

public class Account {

	// daily rates are not handling leap years
	private static double TIER1_THRESHOLD_AMOUNT = 1000.0;
	private static double CHECKING_DAILY_RATE_DEFAULT = 0.001 / 365.0;
	private static double SAVINGS_DAILY_RATE_TIER1 = 0.001 / 365.0;
	private static double SAVINGS_DAILY_RATE_TIER2 = 0.002 / 365.0;
	private static double MAXI_SAVINGS_DAILY_RATE_DEFAULT = 0.001 / 365.0;
	private static double MAXI_SAVINGS_DAILY_RATE_NO_WITHDRAWALS = 0.05 / 365.0;
	private static long MINIMUM_NO_WITHDRAWAL_DAYS = 10;

	private final AccountType accountType;
	public TreeSet<Transaction> transactions;

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new TreeSet<Transaction>();
	}

	public boolean hadRecentWithdrawals(Date asOfDate) {
		boolean rv = false;
		for (Transaction t : transactions) {
			if (t.getAmount() < 0 &&
					MINIMUM_NO_WITHDRAWAL_DAYS > DateProvider.getInstance().calendarDaysDifference
							(t.getDate(), asOfDate)) {
				rv = true;
				break;
			}
		}
		return rv;
	}

	public void deposit(double amount) {
		this.deposit(amount, new Date());
	}

	public void deposit(double amount, Date date) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount, date));
		}
	}

	public void withdraw(double amount) {
		this.withdraw(amount, new Date());
	}

	public void withdraw(double amount, Date date) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount, date));
		}
	}

	/**
	 * Calculate daily interest rate based on account type and the current balance
	 *
	 * @param balance base balance for daily interest calculation
	 * @return
	 */
	public double calculateDailyInterest(double balance) {
		double interest = 0.0;

		switch (accountType) {
			case SAVINGS:
				if (balance <= TIER1_THRESHOLD_AMOUNT) {
					interest = balance * SAVINGS_DAILY_RATE_TIER1;
				} else {
					interest = TIER1_THRESHOLD_AMOUNT * SAVINGS_DAILY_RATE_TIER1;
					interest += (balance - TIER1_THRESHOLD_AMOUNT) * SAVINGS_DAILY_RATE_TIER2;
				}
				break;
			case MAXI_SAVINGS:
				if (balance <= TIER1_THRESHOLD_AMOUNT) {
					interest = balance * SAVINGS_DAILY_RATE_TIER1;
				} else {
					interest = TIER1_THRESHOLD_AMOUNT * SAVINGS_DAILY_RATE_TIER1;
					interest += (balance - TIER1_THRESHOLD_AMOUNT) * SAVINGS_DAILY_RATE_TIER2;
				}
				break;
			default:
				interest = balance * CHECKING_DAILY_RATE_DEFAULT;
		}

		return interest;
	}

	/**
	 * A deprecated interest calculation method
	 *
	 * @return interest earned
	 */
	@Deprecated
	public double interestEarned() {
		double amount = sumTransactions();
		switch (accountType) {
			case SAVINGS:
				if (amount <= 1000)
					return amount * 0.001;
				else
					return 1 + (amount - 1000) * 0.002;
			case MAXI_SAVINGS:
				if (amount <= 1000)
					return amount * 0.02;
				if (amount <= 2000)
					return 20 + (amount - 1000) * 0.05;
				return 70 + (amount - 2000) * 0.1;
			default:
				return amount * 0.001;
		}
	}


	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.getAmount();
		return amount;
	}

	public AccountType getAccountType() {
		return accountType;
	}

}
