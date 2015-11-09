package com.abc;
import java.util.Calendar;
import java.util.Date;
import java.math.BigDecimal;

public class Transaction {
    private final BigDecimal amount;
    private Date transactionDate;
	private String description;
	
	//default Constructor
	public Transaction(){}
	
	//parameterised Constructor
    public Transaction(BigDecimal amount, String desc) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
		this.description = desc
    }

	//getter and setter for transactionDate
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	//getter and setter for description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	//getter for amount
	public BigDecimal getAmount() {
		return amount;
	}

}
