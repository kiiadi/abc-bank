package com.abc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Transaction {
    private final BigDecimal amount;

    private Date transactionDate;
    private String description;
    

    public Transaction(BigDecimal amount, String desc) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
        this.description = desc;
    }


	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public BigDecimal getAmount() {
		return amount;
	}

    
}
