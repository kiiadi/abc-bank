package com.abc.transaction;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.exception.InvalidTransactionException;
import com.abc.util.Constants;
import com.abc.util.Utils;

public abstract class Transaction {
	
	private long transID;
    private BigDecimal transAmount;
    private Date transDate;

    public Transaction(BigDecimal amount) {
		this.transAmount = amount;
        this.transDate = Utils.getNow();
    }
    
	public long getTransID() {
		return transID;
	}

	public void setTransID(long transID) {
		this.transID = transID;
	}

	public BigDecimal getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public BigDecimal process(BigDecimal currentBalance)  throws InvalidTransactionException {
		this.validate(currentBalance);
		return this.processTransaction(currentBalance);
	}

	public abstract BigDecimal processTransaction(BigDecimal currentBalance);
	
	public abstract String getTransactionType();
	
    public void validate(BigDecimal currentBalance) throws InvalidTransactionException {
    	if (getTransAmount() == null || getTransAmount().compareTo(BigDecimal.ZERO) <= 0) {
    		throw new InvalidTransactionException(Constants.INVALID_TRANS_ERROR);
    	}
    }
    
    public String getStatementListing() {
    	StringBuilder statementBuilder = new StringBuilder();
    	statementBuilder.append(Utils.format(getTransDate(), Utils.getYYYY_MM_DD_SimpleDisplayFormat()));
    	statementBuilder.append(Constants.FILLER);
    	statementBuilder.append(getTransactionType());
    	statementBuilder.append(Constants.FILLER);
    	statementBuilder.append(Utils.displayRoundedWithCurrency(getTransAmount()));
    	return statementBuilder.toString();
    }
}
