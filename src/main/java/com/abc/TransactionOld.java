package com.abc;

import java.util.Calendar;
import java.util.Date;

public class TransactionOld {
    public final double amount;

    private Date transactionDate;

    public TransactionOld(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

}
