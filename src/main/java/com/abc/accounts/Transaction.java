/**
 * 
 */
package com.abc.accounts;

import java.util.Date;

import com.abc.utils.DateProvider;

/**
 * An account transaction.
 *
 */
public class Transaction {

	private double amount;
	private Date transactionDate;
	
	public double getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
}
