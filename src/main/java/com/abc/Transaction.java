package com.abc;


import java.util.Date;

public class Transaction {
    private final double amount;//changed to private

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    //Getter function to access private variable transactionDate
    public Date getTranscationDate(){
    	return transactionDate;
    }
    
    //Getter function to access private variable amount
    public double getAmount(){
    	return amount;
    }

}
