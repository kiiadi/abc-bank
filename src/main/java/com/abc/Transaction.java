package com.abc;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {
    private final double amount;
    private final Date date;

	public Transaction(double amount, Date date) {
		this.amount = amount;
		this.date = date;
	}

	public Transaction(double amount) {
        this.amount = amount;
        this.date = DateProvider.getInstance().now();
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
		if (date != null ? !date.equals(that.date) : that.date != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = (int) (temp ^ (temp >>> 32));
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(Transaction o) {
		return this.date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"date=" + date +
				", amount=" + amount +
				'}';
	}
}
