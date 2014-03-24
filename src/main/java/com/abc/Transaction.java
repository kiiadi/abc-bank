package com.abc;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private final BigDecimal amount;

    private final Date transactionDate;
    // joda immutable date/time (http://www.joda.org) would be better choice
    private Date date;

    public Transaction(BigDecimal amount) {
        this(amount, DateProvider.getInstance().now());
    }

    public Transaction(BigDecimal amount, Date date) {
        this.amount = amount;
        this.transactionDate = new Date(date.getTime());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return new Date(transactionDate.getTime());
    }

    public boolean isDeposit() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isWithdrawal() {
        return !isDeposit();
    }
}
