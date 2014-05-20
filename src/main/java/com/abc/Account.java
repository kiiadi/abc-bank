package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.JournalEntry;

public class Account implements AccountDetail {

	private final int m_accountType;
	private double m_balance;
	public List<Transaction> m_transactions;

	public Account(int accountType) {
		m_accountType = accountType;
		m_transactions = new ArrayList<Transaction>();
	}

	public JournalEntry createNewTransaction(Date transactionDate, double amount, int transactionType) {
		Transaction t = new Transaction(transactionDate, amount, transactionType);
		m_transactions.add(t);
		m_balance += t.getTransactionAmount();
		return t;
	}

	public double getBalacne() {
		return m_balance;
	}

	public int getAccountType() {
		return m_accountType;
	}

}
