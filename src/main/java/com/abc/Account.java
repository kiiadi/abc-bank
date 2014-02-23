package com.abc;

import java.util.Date;
import java.util.TreeSet;

public abstract class Account {

	private double balance;
	private double interest;

	// use TreeSet to house transactions ordered chronologically
	private TreeSet<Transaction> transactions;

	/**
	 * default constructor;
	 */
	public Account() {
		this.balance = 0.0;
		this.interest = 0.0;
		this.transactions = new TreeSet<>();
	}

	/**
	 * Get the account type name, abstract method intended to be implemented in the sub-classes
	 *
	 * @return account type name
	 */
	public abstract String getTypeName();

	/**
	 * Calculate one day's worth of interest.  Abstract method intended to be implemented by the the specific
	 * sub-classes
	 *
	 * @param balance base balance for daily interest calculation
	 * @return one day's interest amount
	 */
	public abstract double calculateDailyInterest(double balance, Date asOfDate);


	public TreeSet<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Create a deposit/credit transaction as of the current date/time
	 *
	 * @param amount amount to deposit
	 */
	public void deposit(double amount) {
		this.deposit(amount, new Date());
	}

	/**
	 * Create a deposit/credit transaction on a specified transaction date
	 *
	 * @param amount amount to deposit
	 * @param date   transaction date of the deposit
	 */
	public void deposit(double amount, Date date) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		}

		transactions.add(new Transaction(amount, date));
		calculateBalanceAndInterest(date);
	}

	/**
	 * Create a withdrawal/debit transaction as of the current date/time
	 *
	 * @param amount amount to withdraw
	 */
	public void withdraw(double amount) throws AccountModificationException {
		this.withdraw(amount, new Date());
	}

	/**
	 * Create a withdrawal/debit transaction on a specified transaction date
	 *
	 * @param amount amount to withdraw
	 * @param date   transaction date of the withdrawal
	 */
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

	/**
	 * Get current account balance
	 *
	 * @return account balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Get current accrued interest
	 *
	 * @return interest
	 */
	public double getInterest() {
		return interest;
	}

	/**
	 * Calculate the account balance as well as total accrued interest by applying transaction amounts
	 * starting with account opening and ending with a given asOfDate. Use daily interest compounding.
	 *
	 * @param asOfDate date to accrue interest up to
	 * @return accrued interest value
	 */
	public double calculateBalanceAndInterest(Date asOfDate) {
		interest = 0.0;
		balance = 0.0;

		if (transactions.size() <= 0) {
			return 0;
		}

		// begin calculations starting from the first chronological transaction
		Transaction firstTransaction = transactions.first();
		asOfDate = DateUtil.isolateDateComponents(asOfDate);
		Date runnerDate = DateUtil.isolateDateComponents(firstTransaction.getDate());

		while (runnerDate.compareTo(asOfDate) <= 0) {

			// calculate interest and apply the result to balance
			double interest = calculateDailyInterest(balance, runnerDate);
			balance += interest;
			this.interest += interest;

			// iterate over transactions to apply the day's transactions' amounts to balance
			for (Transaction t : transactions) {
				Date transactionDate = DateUtil.isolateDateComponents(t.getDate());
				if (transactionDate.compareTo(runnerDate) == 0) {
					balance += t.getAmount();
				}
			}

			// advance the runner date
			runnerDate = DateUtil.addDays(runnerDate, 1);
		}

		return interest;
	}

	/**
	 * Determine whether there were had been withdrawal transactions with N previous days of asOfDate
	 *
	 * @param asOfDate as-of date
	 * @param nDays    number of days
	 * @return true if there are withdrawal transactions within N previous days
	 */
	protected boolean hadRecentWithdrawals(Date asOfDate, int nDays) {
		boolean rv = false;
		for (Transaction t : transactions) {
			if (t.getDate().compareTo(asOfDate) > 0) {
				// skip checking future transactions
				break;
			}

			// debit transaction within previous nDays
			if (t.getAmount() < 0 &&
					nDays > DateUtil.calendarDaysDifference(t.getDate(), asOfDate)) {
				rv = true;
				break;
			}
		}
		return rv;
	}
}
