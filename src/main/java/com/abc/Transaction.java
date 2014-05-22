package com.abc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Transaction {
    private final BigDecimal amount;

    private final Date transactionDate;

    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    public Transaction(BigDecimal amount, int dayInterval) {
        this.amount = amount;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -dayInterval);
        this.transactionDate = c.getTime();
    }

	public BigDecimal getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

}
