package com.abc;

import java.math.BigDecimal;
import java.util.Date;

import com.abc.BankConstants.TransactionType;

public class Transaction {
	public final Integer _transactionId; 
    public final BigDecimal _amount;
    public final Integer _accountId;
    public final Integer _customerId;
    private final Date _transactionDate;
    private final TransactionType _transactionType;
    
	public Transaction(Integer transactionId_ , BigDecimal amount_, Integer accountId_,
			Integer customerId_, Date transactionDate_,
			TransactionType transactionType_) {
		_transactionId  = transactionId_;
		_amount = amount_;
		_accountId = accountId_;
		_customerId = customerId_;
		_transactionDate = transactionDate_;
		_transactionType = transactionType_;
	}
	public BigDecimal getAmount() {
		return _amount;
	}
	public Integer getAccountId() {
		return _accountId;
	}
	public Integer getCustomerId() {
		return _customerId;
	}
	public Date getTransactionDate() {
		return _transactionDate;
	}
	public TransactionType getTransactionType() {
		return _transactionType;
	} 
}
