package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    public Transaction(double amount, int days_before){
    	this.amount = amount;
    	long days = (long) (10*((1000*60*60*24)));
    	//Long days = TimeUnit.MILLISECONDS.convert(days_before, TimeUnit.DAYS);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(calendar.getTimeInMillis() - days);
    	Date previous_date = calendar.getTime();
    	this.transactionDate = previous_date;
    }
    
    public Transaction(double amount, Date date){
    	this.amount = amount;
    	this.transactionDate = date;
    }
    
    public Date getTransactionDate(){
    	return transactionDate;
    }

}

