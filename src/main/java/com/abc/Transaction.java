package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

    public Transaction(double amount, int daysAgo) {
        if(daysAgo<0){
            throw new IllegalArgumentException("transaction shouldn't happen in the future");
        }
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().daysAgo(daysAgo);
    }

    public boolean isWithdraw(){
        return amount<0;
    }

    public boolean isDeposit(){
        return amount>0;
    }

    public int daysFromNow(){
        return (int)((DateProvider.getInstance().now().getTime() - this.transactionDate.getTime())/(1000*60*60*24));
    }
}
