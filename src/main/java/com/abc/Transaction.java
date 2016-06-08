package com.abc;

import java.util.Date;

public class Transaction {
    private final Date transactionDate;
    private final TransactionType transactionType;
    public final double amount;

    /**
     * Constructor for creating new transaction objects.
     * 
     * 
     * @param occured             time the transaction look place
     * @param type  the type of transaction
     * @param a           The amount involved
     */
    public Transaction(Date occured, TransactionType type, double amount) {
    	transactionDate = occured;
        transactionType = type;
        this.amount = amount;
    }

	public Date getTransactionDate() {
		return transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public double getAmount() {
		return amount;
	}
    

   
    
  

}
