package com.abc;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private final BigDecimal amount;
    private final TransactionType transactionType;


    private Date transactionDate;

    public Transaction(BigDecimal amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = DateProvider.now();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }


}
