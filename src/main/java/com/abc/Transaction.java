package com.abc;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    final BigDecimal amount;

    final Date transactionDate;

    final boolean withdrawFlag;

    public Transaction(BigDecimal amount, boolean withdrawFlag) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
        this.withdrawFlag = withdrawFlag;
    }

}
