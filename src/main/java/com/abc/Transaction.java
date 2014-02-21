package com.abc;

import java.util.Date;

/**
 * Class to map Transactions
 * @author Prachi
 *
 */
public class Transaction {
	
	/**
	 * amount for a particular transaction
	 */
    private double amount;

    /**
     * Date of a particular transaction
     */
    private Date transactionDate;
    
    /**
     * Initialize a Transaction with amount and date
     * @param amount
     * @param transDate
     */
    public Transaction(double amount,Date transDate) {
        this.amount = amount;
        this.transactionDate = transDate;
    }

    /**
     * Getter to get Transaction amount
     * @return
     */
	public double getAmount() {
		return amount;
	}
	
	/**
     * Getter to get Transaction date
     * @return
     */
	public Date getTransactionDate() {
		return transactionDate;
	}
}
