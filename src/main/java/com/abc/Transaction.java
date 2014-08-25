package com.abc;

// Removed the unused import Calendar
import java.util.Date;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.now();
    }
    
    //Return Defensive copy, so that date is not modified by the caller.
    public Date getTransactionDate(){
      return new Date(this.transactionDate.getTime());
    }

}
