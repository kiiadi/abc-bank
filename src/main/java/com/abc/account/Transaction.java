package com.abc.account;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.util.DateProvider;

/**
 * Transaction is immutable as the fields are all immutable types and there are no setters for
 * the fields. The only field that is mutable is transactionDate of type Date for which the getter returns a copy
 * of the date.
 * @author ashok
 *
 */
public class Transaction {
	
    private BigDecimal amount;

    private Date transactionDate;
    
    private TransactionType type;

    public Transaction(BigDecimal amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
        this.transactionDate = DateProvider.getInstance().now();
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
