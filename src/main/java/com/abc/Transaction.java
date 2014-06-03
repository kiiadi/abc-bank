package com.abc;

import java.util.Date;

/**
 * The Class Transaction.
 * Instances of this class will be immutable.
 */
public class Transaction {
	
	/** The constant DEPOSIT */
	public static final int DEPOSIT=0;
	
	/** The constant WITHDRAWAL */
	public static final int WITHDRAWAL=1;
	
	
	public static DateProvider dateProvider = null;
    
	
    /** The amount. */
    private final double transactionAmount;
    
    /** The tranactionType */
    private final int transactionType;

    /** The transaction date. */
    private final Date transactionDate;
    
    
    /**
     *  Static initializer for the local DateProvider instance
     */
    static
    {
    	dateProvider = DateProvider.getInstance();
    }
    
    
    /**
     * Instantiates a new immutable transaction.
     *
     * @param amount the amount
     */
    public Transaction(double amount, int type) {
        this.transactionAmount = amount;
        this.transactionType = type;
        this.transactionDate = dateProvider.now();
    }
    
    
    /**
     * Return the transaction amount.
     * 
     * @return the amount
     */
    public double getTransactionAmount() {
    	return transactionAmount;
    }
    
    
    /**
     * Return the transactionType.
     * 
     * @return the transactionType
     */
    public int getTransactionType() {
    	return transactionType;
    }
     
    
    /**
     * Return the transactionDate.
     * 
     * @return the transactionDate
     */
    public Date getTransactionDate() {
    	return transactionDate;
    }

}
