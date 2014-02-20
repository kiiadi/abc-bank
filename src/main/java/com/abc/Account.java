package com.abc;

import java.util.Date;
import java.util.TreeSet;

public class Account {

	// current implementation does not handle leap years
	private static double TIER1_THRESHOLD_AMOUNT = 1000.0;
	private static double CHECKING_DAILY_RATE_DEFAULT = 0.001 / 365.0;
	private static double SAVINGS_DAILY_RATE_TIER1 = 0.001 / 365.0;
	private static double SAVINGS_DAILY_RATE_TIER2 = 0.002 / 365.0;
	private static double MAXI_SAVINGS_DAILY_RATE_DEFAULT = 0.001 / 365.0;
	private static double MAXI_SAVINGS_DAILY_RATE_NO_WITHDRAWALS = 0.05 / 365.0;
	private static long MINIMUM_NO_WITHDRAWAL_DAYS = 10;

	private final AccountType accountType;

	// use TreeSet to house transactions ordered chronologically
	private TreeSet<Transaction> transactions;
	private double balance;
	private double accruedInterest;

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new TreeSet<Transaction>();
	}

	public void deposit(double amount) {
		this.deposit(amount, new Date());
	}

	public void deposit(double amount, Date date) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		}

		transactions.add(new Transaction(amount, date));
		calculateBalanceAndInterest(date);
	}

	public void withdraw(double amount) throws AccountModificationException {
		this.withdraw(amount, new Date());
	}

	public void withdraw(double amount, Date date) throws AccountModificationException {
		if (amount > balance) {
			throw new AccountModificationException("insufficient funds");
		}

		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		}

		transactions.add(new Transaction(-amount, date));
		calculateBalanceAndInterest(date);
	}

	public double getBalance() {
		return balance;
	}

	public double getAccruedInterest() {
		return accruedInterest;
	}

	/**
	 * Calculate the account balance as well as total accrued interest
	 * since the account opening and up to asOfDate, using daily interest compounding.
	 * Calculate the total account balance as well.
	 *
	 * @param asOfDate date to accrue interest up to
	 * @return accrued interest value
	 */
	public double calculateBalanceAndInterest(Date asOfDate) {
		accruedInterest = 0.0;
		balance = 0.0;

		if (transactions.size() <= 0) {
			return 0;
		}

		// begin calculations starting from the first chronological transaction
		Transaction firstTransaction = transactions.first();
		asOfDate = DateProvider.getInstance().isolateDateComponents(asOfDate);
		Date runnerDate = DateProvider.getInstance().isolateDateComponents(firstTransaction.getDate());

		while (runnerDate.compareTo(asOfDate) <= 0) {

			// calculate interest and apply the result to balance
			double interest = calculateDailyInterest(balance, runnerDate);
			balance += interest;
			accruedInterest += interest;

			// iterate over transactions to apply the day's transactions' amounts to balance
			for (Transaction t : transactions) {
				Date transactionDate = DateProvider.getInstance().isolateDateComponents(t.getDate());
				if (transactionDate.compareTo(runnerDate) == 0) {
					balance += t.getAmount();
				}
			}

			// advance the runner date
			runnerDate = DateProvider.getInstance().addDays(runnerDate, 1);
		}

		return accruedInterest;
	}

	/**
	 * Calculate one day's worth of interest, based on the current balance and account type
	 *
	 * @param balance base balance for daily interest calculation
	 * @return
	 */
	public double calculateDailyInterest(double balance, Date asOfDate) {
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
				double dailyRate = (hadRecentWithdrawals(asOfDate)) ?
						MAXI_SAVINGS_DAILY_RATE_DEFAULT : MAXI_SAVINGS_DAILY_RATE_NO_WITHDRAWALS;

				interest = balance * dailyRate;
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

	boolean hadRecentWithdrawals(Date asOfDate) {
		boolean rv = false;
		for (Transaction t : transactions) {
			if (t.getAmount() < 0 &&
					t.getDate().compareTo(asOfDate) <= 0 &&
					MINIMUM_NO_WITHDRAWAL_DAYS > DateProvider.getInstance().calendarDaysDifference
							(t.getDate(), asOfDate)) {
				rv = true;
				break;
			}
		}
		return rv;
	}

	public TreeSet<Transaction> getTransactions() {
		return transactions;
	}

	public AccountType getAccountType() {
		return accountType;
	}
}
