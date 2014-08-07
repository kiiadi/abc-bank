package com.abc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * The class keeps information about transaction.<br>
 * After transaction creating it is not allowed to change its state.
 */
public class Transaction {
    private final double amount;
    private final Date transactionDate;

    /**
     * Create Transaction instance for given amount with current transaction date.
     */
    public Transaction(double amount) {
        this.amount = amount;
        transactionDate = Calendar.getInstance().getTime();
    }
    
    /**
     * Create Transaction instance for given amount and transaction date.
     */
    public Transaction(double amount, Date date) {
    	this.amount = amount;
    	transactionDate = date;
    }
    
    /**
     * Return transaction amount.
     */
    public double getAmount() {
    	return amount;
    }
    
    /**
     * Return transaction date.
     */
    public Date getDate() {
    	return transactionDate;
    }
    
    @Override
    public String toString() {
    	return String.valueOf(amount) + " " + new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(transactionDate);
    }

}
