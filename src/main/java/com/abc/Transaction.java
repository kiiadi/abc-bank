package com.abc;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
public class Transaction {

    public final BigDecimal amount;

    private Date transactionDate;

    private BigDecimal resultingBalance;


    public Transaction(BigDecimal amount, Date transactionDate, BigDecimal resultingBalance) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.resultingBalance = resultingBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getResultingBalance() {
        return resultingBalance;
    }

    public boolean isWithdrawal() {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }
}