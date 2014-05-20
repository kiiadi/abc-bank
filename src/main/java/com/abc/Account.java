package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.JournalEntry;
import com.abc.interfaces.TransactionType;

public class Account implements AccountDetail {

	private final int m_accountType;
	public List<Transaction> m_transactions;

	public Account(int accountType) {
		m_accountType = accountType;
		m_transactions = new ArrayList<Transaction>();
	}

	public JournalEntry createNewTransaction(Date transactionDate, double amount, int transactionType) {		
		Transaction t = new Transaction(transactionDate, amount, transactionType);
		m_transactions.add(t);
		return t;
	}

	public double interestEarned() {
		double amount = sumTransactions();
		switch (m_accountType) {
		case AccountType.SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;
			// case SUPER_SAVINGS:
			// if (amount <= 4000)
			// return 20;
		case AccountType.MAXI_SAVINGS:
			if (amount <= 1000)
				return amount * 0.02;
			if (amount <= 2000)
				return 20 + (amount - 1000) * 0.05;
			return 70 + (amount - 2000) * 0.1;
		default:
			return amount * 0.001;
		}
	}

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : m_transactions)
			amount += t.getTransactionAmount();
		return amount;
	}

	public int getAccountType() {
		return m_accountType;
	}

}
