package com.abc;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

	private final BigDecimal amount;

	private Date transactionDate;

	public Transaction(BigDecimal amount) {
		this.amount = amount;
		this.setTransactionDate(DateProvider.getInstance().now());
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
