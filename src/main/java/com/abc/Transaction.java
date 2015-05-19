package com.abc;

import java.math.BigDecimal;
import java.util.Date;

//immutable class
public class Transaction {

    private final BigDecimal amount;
    private final Date transactionDate;
    private TransactionType type;

    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
        this.type = amount.compareTo(BigDecimal.ZERO) > 0 ? TransactionType.deposit : TransactionType.withdraw;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return new Date(transactionDate.getTime());
    }

    public TransactionType getType() {
        return type;
    }
}
