package com.abc;

import java.util.Calendar;
import java.util.Date;

import com.abc.util.Utils;

public class Transaction implements Comparable<Transaction>{
	private static long transactionId = 1;
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this(amount, Utils.getInstance().dateNow());
        transactionId++;
    }
    /**
     * 
     * @param amount
     * @param transactionDate	Passing Transaction date as parameter will increase testability
     */
    public Transaction(double amount, Date transactionDate) {
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
    
    public long getTransactionDateTime(){
    	return transactionDate.getTime();
    }
    
	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", transactionDate="
				+ transactionDate + "]";
	}
	@Override
	public int compareTo(Transaction other) {
		if(other == null) return 1;
		if(this.getTransactionDateTime() == other.getTransactionDateTime()){
			return (int) (this.transactionId - other.transactionId);
		}
		return (this.getTransactionDateTime() > other.getTransactionDateTime() ? 1 : -1);
	}

}
