package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
 
	private  final double amount;
	private final Date transactionDate; 
    private final TransactionType type ;

    public Transaction(TransactionType type, double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    public double getAmount() {
		return amount;
	}
	public TransactionType getType() {
		return type;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s \n", getTransactionDate().toString(),
					getType().toString(),
					Double.toString(getAmount()));
	}
	
	

}
