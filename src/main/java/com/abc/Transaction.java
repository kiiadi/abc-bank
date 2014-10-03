package com.abc;

import java.util.Date;

/**
 * This abstract class represents any transactions that can be added to a bank account.  
 * @author qyang28
 *
 */
public abstract class Transaction {
	
	/**
	 * amount of the transaction. Account amount increases with this transaction if amount is a positive number.
	 */
    public final double amount;

    private Date transactionDate;

    /**
     * Construct an object of transaction.
     * @param amount
     */
    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    /**
     * The date when the transaction was created.
     * @return transaction date.
     */
    public Date getTransactionDate() {
    	return transactionDate;
    }
    
    /**
     * Details of the transaction.
     * @return details of the transaction
     */
    public abstract String print();
    
    // this helper method is used for testing purpose
    protected void setDate(Date date) {
        this.transactionDate = date;
    }
}
