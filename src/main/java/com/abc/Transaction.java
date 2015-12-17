package com.abc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public final class Transaction {
    public final BigDecimal amount;

    private final Date transactionDate;

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }



}
