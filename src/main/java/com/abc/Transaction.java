package com.abc;

import java.util.Date;

public class Transaction {

	private double amount;
	private Date transactionDate;
	private TransactionType transactType;

	public String printTransaction() {
		iReportBuilder builder = new TransactionBuilder();
		return builder.build(this);
	}

	public Transaction(double amount) {
		if (amount == BankConstants.ZERO_AMOUNT)
			throw new IllegalArgumentException(BankConstants.INVALID_AMOUNT);
		this.amount = amount;
		this.transactType = (amount > 0 ? TransactionType.DEPOSIT
				: TransactionType.WITHDRAWAL);
		transactionDate = Utility.now();
	}

	public TransactionType getTransactType() {
		return transactType;
	}

	public void setTransactType(TransactionType transactType) {
		this.transactType = transactType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", transactionDate="
				+ transactionDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}

}
