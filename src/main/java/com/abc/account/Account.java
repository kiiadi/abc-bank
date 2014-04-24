package com.abc.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.abc.DateProvider;
import com.abc.Transaction;

/**
 * Base class for concrete Account classes. Subclasses will override abstract
 * methods and provide concrete implementations.
 * 
 */
public abstract class Account {

	protected String label;

	private List<Transaction> transactions;

	public Account() {
		this.transactions = new ArrayList<Transaction>();
	}

	public synchronized void deposit(BigDecimal amount) {

		if (amount.compareTo(new BigDecimal("0.00")) <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		transactions.add(new Transaction(amount));

	}

	public synchronized void withdraw(BigDecimal amount) throws Exception {

		if (amount.compareTo(new BigDecimal("0.00")) <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}

		if (amount.compareTo(sumTransactions()) > 0) {
			throw new Exception("insufficient funds on withdrawal");
		}

		transactions.add(new Transaction(amount.negate()));

	}

	public abstract BigDecimal interestEarned();

	public BigDecimal sumTransactions() {

		BigDecimal total = new BigDecimal("0.00");

		for (Transaction t : transactions) {

			total = total.add(t.getAmount());

		}

		return total.setScale(2, BigDecimal.ROUND_CEILING);
	}
	
	public BigDecimal sumTransactionsForDate(final Date date) {

		BigDecimal total = new BigDecimal("0.00");

		for (Transaction t : transactions) {
			
			if (t.getTransactionDate().equals(date)) {
				total = total.add(t.getAmount());
			}
		}

		return total.setScale(2, BigDecimal.ROUND_CEILING);
	}

	public void sortTransactionsAscending() {
		Collections.sort(transactions, new Comparator<Transaction>() {

			public int compare(Transaction t1, Transaction t2) {
				return t1.getTransactionDate().compareTo(
						t2.getTransactionDate());
			}
		});
	}


	public ArrayList<BigDecimal> getDailyBalances() {
		
		ArrayList<BigDecimal> dailyBalances = new ArrayList<BigDecimal>();
		
		this.sortTransactionsAscending();
		
		Transaction previous = null;
		
		for (Transaction transaction : transactions) {

			if (previous != null) {
				
				// compute days between current and prev
				
				long diff = transaction.getTransactionDate().getTime() - previous.getTransactionDate().getTime();
				long diffInDays = diff / (24 * 60 * 60 * 1000);
				
				
				// get sum of transactions for prev's transaction date
				BigDecimal prevDateSum =  this.sumTransactionsForDate(previous.getTransactionDate());
				BigDecimal previousBalance = null;
				
				if (dailyBalances.isEmpty()) {
					previousBalance = new BigDecimal("0.00");
				} else {
					previousBalance = dailyBalances.get(dailyBalances.size() - 1);
				}
				// compute daily balance
				BigDecimal dailyBalance = prevDateSum.add(previousBalance);
				
				// add entries for number of days
				for (int i = 0; i < diffInDays; i++) {
					dailyBalances.add(dailyBalance);
				}
				
			}
			
			previous = transaction;
			
		}
		
		// determine sum of transactions for prev's date 
		BigDecimal prevDateSum =  this.sumTransactionsForDate(previous.getTransactionDate());
		
		// compute the difference based on the latest entry
		BigDecimal previousBalance = null;
		if (dailyBalances.isEmpty()) {
			previousBalance = new BigDecimal("0.00");
		} else {
			previousBalance = dailyBalances.get(dailyBalances.size() - 1);
		}
		// compute daily balance
		BigDecimal dailyBalance = prevDateSum.add(previousBalance);
		
		// compute number of days between prevs date and now 
		long diff = DateProvider.getInstance().now().getTime() - previous.getTransactionDate().getTime();
		long diffInDays = diff / (24 * 60 * 60 * 1000);
		
		// make that many entries
		for (int i = 0; i < diffInDays ; i++) {
			dailyBalances.add(dailyBalance);
		}
	
		return dailyBalances;
		
	}
	
	

	public String getLabel() {
		return label;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

}
