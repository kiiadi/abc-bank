package com.abc;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TreeSet;

public class Account {

	// constants used by te current interest calculation logic
	// implementation does not handle leap years
	private static double TIER1_THRESHOLD_AMOUNT = 1000.0;
	private static double CHECKING_DAILY_RATE_DEFAULT = 0.001 / 365.0;
	private static double SAVINGS_DAILY_RATE_TIER1 = 0.001 / 365.0;
	private static double SAVINGS_DAILY_RATE_TIER2 = 0.002 / 365.0;
	private static double MAXI_SAVINGS_DAILY_RATE_DEFAULT = 0.001 / 365.0;
	private static double MAXI_SAVINGS_DAILY_RATE_NO_WITHDRAWALS = 0.05 / 365.0;
	private static long MINIMUM_NO_WITHDRAWAL_DAYS = 10;
	private static NumberFormat usaFormat = NumberFormat.getCurrencyInstance(Locale.US);
	private final AccountType accountType;

	// use TreeSet to house transactions ordered chronologically
	private TreeSet<Transaction> transactions;
	private double balance;
	private double accruedInterest;

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new TreeSet<>();
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
	 * 	Get current account balance
	 * @return account balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Calculate the account balance as well as total accrued interest by applying transaction amounts
	 * starting with account opening and ending with a given asOfDate. Use daily interest compounding.
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
		asOfDate = DateUtil.getInstance().isolateDateComponents(asOfDate);
		Date runnerDate = DateUtil.getInstance().isolateDateComponents(firstTransaction.getDate());

		while (runnerDate.compareTo(asOfDate) <= 0) {

			// calculate interest and apply the result to balance
			double interest = calculateDailyInterest(balance, runnerDate);
			balance += interest;
			accruedInterest += interest;

			// iterate over transactions to apply the day's transactions' amounts to balance
			for (Transaction t : transactions) {
				Date transactionDate = DateUtil.getInstance().isolateDateComponents(t.getDate());
				if (transactionDate.compareTo(runnerDate) == 0) {
					balance += t.getAmount();
				}
			}

			// advance the runner date
			runnerDate = DateUtil.getInstance().addDays(runnerDate, 1);
		}

		return accruedInterest;
	}

	/**
	 * Calculate one day's worth of interest, based on the current balance and account type
	 *
	 * @param balance base balance for daily interest calculation
	 * @return one day's interest amount
	 */
	double calculateDailyInterest(double balance, Date asOfDate) {
		double interest;

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
	 * Determine whether there were any withdrawal transactions with N days of asOfDate
	 *
	 * @param asOfDate as-of date
	 * @return true if there are withdrawal transactions within N days
	 */
	boolean hadRecentWithdrawals(Date asOfDate) {
		boolean rv = false;
		for (Transaction t : transactions) {
			if (t.getAmount() < 0 &&
					t.getDate().compareTo(asOfDate) <= 0 &&
					MINIMUM_NO_WITHDRAWAL_DAYS > DateUtil.getInstance().calendarDaysDifference
							(t.getDate(), asOfDate)) {
				rv = true;
				break;
			}
		}
		return rv;
	}

	/**
	 * Generate an account statement string
	 *
	 * @return String representing an account statement
	 */
	public String statement() {
   		calculateBalanceAndInterest(new Date());

		// Start with pretty account type
		StringBuffer s = new StringBuffer(accountType.toString());
		s.append("\n");

		// List transactions
		for (Transaction t : transactions) {
			s.append("  ").append(t.getAmount() < 0 ? "withdrawal" : "deposit").append(" ");
			s.append(usaFormat.format(Math.abs(t.getAmount()))).append("\n");
		}

		// Account accrued interest
		s.append("Accrued Interest ").append(usaFormat.format(accruedInterest)).append("\n");

		// Account balance
		s.append("Total ").append(usaFormat.format(balance));

		return s.toString();
	}
}
