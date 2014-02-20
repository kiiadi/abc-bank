package com.abc;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {
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
		this.date = DateProvider.getInstance().now();
		this.id = IdGenerator.getInstance().getId();
	}

	public Date getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Transaction that = (Transaction) o;

		if (Double.compare(that.amount, amount) != 0) return false;
		if (id != that.id) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = (int) (id ^ (id >>> 32));
		temp = Double.doubleToLongBits(amount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

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
