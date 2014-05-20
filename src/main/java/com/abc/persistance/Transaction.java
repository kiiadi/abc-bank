package com.abc.persistance;

import java.util.Date;

import com.abc.interfaces.JournalEntry;

public class Transaction implements JournalEntry {
	public double m_amount;
	private Date m_transactionDate;
	private int m_transactionType;

	public Transaction(Date transactionDate, double amount, int transactionType) {
		m_amount = amount;
		m_transactionDate = transactionDate;
		m_transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return m_transactionDate;
	}

	public int getTransactionType() {
		return m_transactionType;
	}

	public double getAmount() {
		return m_amount;
	}

	public double getTransactionAmount() {
		return getAmount() * getTransactionType();
	}

}
