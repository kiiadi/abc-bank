package com.abc;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {

	// a transaction id uniquely identifies a transaction
	private long id;
	private final double amount;
	private final Date date;

	public Transaction(double amount, Date date) {
		this.amount = amount;
		this.date = date;
		this.id = IdGenerator.getInstance().getId();
	}

	public Transaction(double amount) {
		this.amount = amount;
		this.date = DateUtil.getInstance().now();
		this.id = IdGenerator.getInstance().getId();
	}

	public Date getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

	/**
	 * compareTo method will be used by the collection housing transactions
	 * in transaction date order
	 *
	 * @param rhs "right-hand-side" transaction to compare "this" transaction to
	 * @return standard compareTo() integer return code
	 */
	@Override
	public int compareTo(Transaction rhs) {
		int rv = this.date.compareTo(rhs.date);
		if (rv == 0) {
			if (id < rhs.id) {
				rv = -1;
			} else if (id > rhs.id) {
				rv = 1;
			} else {
				rv = 0;
			}
		}
		return rv;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"id=" + id +
				", date=" + date +
				", amount=" + amount +
				'}';
	}
}
