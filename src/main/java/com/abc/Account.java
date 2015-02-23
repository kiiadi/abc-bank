package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	private final int accountType;
	private List<Transaction> transactions;
	private double balance;

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
		this.balance = 0.0;
	}
	
	private void checkAmount(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} 
	}

	public void deposit(double amount) {
		checkAmount(amount);
		synchronized (transactions) {
			balance += amount;
			transactions.add(new Transaction(amount));
		}
	}

	public void withdraw(double amount) {
		checkAmount(amount);
		synchronized (transactions) {
			//TODO: need to clarify - assume we don't allow overdraw
			if (balance < amount)
				throw new IllegalStateException("insufficient fund");
			balance -= amount;
			transactions.add(new Transaction(-amount));
		}
	}

	/**
	 * Returns a {@link Calendar} presenting {@code n}-days from the {@code ref}.
	 * 
	 * <pre>{@code Calendar jan27 = addDays(feb10, -14);}</pre>
	 * @param ref the reference {@code Calendar} instance.
	 * @param n the number of days the returning {@code Calendar} is apart from the reference.
	 * @return a Calendar presenting n-days from the reference.
	 */
	public static Calendar addDays(Calendar ref, int n) {
		Calendar date;
		date = (Calendar) ref.clone();
		date.add(Calendar.DATE, n);
		return date;
	}
	
	/**
	 * Returns the diff (in days) between 2 dates. <p/>
	 * 
	 * <b>Caution:</b> This method assumes the time parts of the 2 given dates
	 * are same. If this is not the case, the result may not be accurate. 
	 * Consider using {@link #removeTime(Calendar)} to avoid the trouble.
	 * @param start
	 * @param end
	 * @return the diff (in days) between 2 dates
	 */
	public static long diffDays(Calendar start, Calendar end) {
		if (start.after(end))
			throw new IllegalArgumentException("start date cannot be after end date.");
		// Use Math.round to take care the DST impact
		return Math.round((end.getTimeInMillis() - start.getTimeInMillis()) / (24 * 60 * 60 * 1000.0));
	}

	/**
	 * Reset the {@link Calendar} instance back to the midnight.
	 * @param from
	 * @return
	 */
	public static void removeTime(Calendar from) {
		from.set(Calendar.HOUR_OF_DAY, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.MILLISECOND, 0);
	}
	
	public static Calendar toCalenderDate(Date start) {
		Calendar from = Calendar.getInstance();
		from.setTime(start);
		// wipe off the time fields
		removeTime(from);
		return from;
	}

	public static double interestInDateRange(double balance, double dailyRate, Calendar start, Calendar end) {
		long days = diffDays(start, end);
		return days == 0 ? 0.0 : days * dailyRate * balance;
	}

	public double interestEarned() {
		//double amount = sumTransactions();
		List<Transaction> transactions;
		synchronized (this.transactions) {
			if(this.transactions.isEmpty()) {
				return 0.0;
			}
			transactions = new ArrayList<Transaction>(this.transactions);
		}
		double interest = 0.0;
		double baseRate = 0.001 / 365;
		Iterator<Transaction> it = transactions.iterator();
		Transaction first = it.next();
		Calendar start = toCalenderDate(first.getTransactionDate());
		Calendar today = toCalenderDate(DateProvider.getInstance().now());
		double balance = first.amount;
		switch (accountType) {
		case SAVINGS:
			while(it.hasNext()) {
				Transaction trans = it.next();
				Calendar end = toCalenderDate(trans.getTransactionDate());
//				if (balance <= 1000) {
//					interest += interestInDateRange(balance, baseRate, start, end);
//				} else {
//					interest += interestInDateRange(1000, baseRate, start, end);
//					interest += interestInDateRange(balance - 1000, baseRate*2, start, end);
//				}
				
				// balance over $1000 has a interest rate of 2%, which doubles the base rate of 1%
				double effectiveBalance = balance <= 1000 ? balance : 2*balance-1000;
				interest += interestInDateRange(effectiveBalance, baseRate, start, end);
				balance += trans.amount;
				start = end;
			}
			// use the same effective balance trick on the last period
			interest += interestInDateRange(balance <= 1000 ? balance : 2*balance-1000, baseRate, start, today);
			break;
		case MAXI_SAVINGS:
			double bonusRate = 0.05 / 365;
			// Does it really make sense to start 5% from day-1? Or maybe from the 10th?
			double dailyRate = bonusRate;
			Calendar bonusStart = null;
			if (balance < 0) {
				// Impossible if we forbid overdraw in #withdraw(), but if it's allowed...
				bonusStart = addDays(start, 10);
				dailyRate = baseRate;
			}
			while(it.hasNext()) {
				Transaction trans = it.next();
				Calendar end = toCalenderDate(trans.getTransactionDate());
				if (bonusStart != null && !end.before(bonusStart)) {
					// no withdraw in the past 10 days
					interest += interestInDateRange(balance, dailyRate, start, bonusStart);
					start = bonusStart;
					bonusStart = null;
					dailyRate = bonusRate;
				}
				interest += interestInDateRange(balance, dailyRate, start, end);
				balance += trans.amount;
				if (trans.amount < 0) {
					bonusStart = addDays(end, 10);
					dailyRate = baseRate;
				} 
				start = end;
			}
			// may encounter bonus rate in the last period too
			if (bonusStart != null && !today.before(bonusStart)) {
				interest += interestInDateRange(balance, dailyRate, start, bonusStart);
				start = bonusStart;
				//bonusStart = null;
				dailyRate = bonusRate;
			}
			interest += interestInDateRange(balance, dailyRate, start, today);
			break;
		default: // CHECKING
			while(it.hasNext()) {
				Transaction trans = it.next();
				Calendar end = toCalenderDate(trans.getTransactionDate());
				interest += interestInDateRange(balance, baseRate, start, end);
				balance += trans.amount;
				start = end;
			}
			// the last period is from the last transaction date to now
			interest += interestInDateRange(balance, baseRate, start, today);
		}
		return interest;
	}

	public double sumTransactions() {
		double amount = 0.0;
		synchronized(transactions) {
			for (Transaction t : transactions)
				amount += t.amount;
		}
		return amount;
	}

	public int getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}
}
