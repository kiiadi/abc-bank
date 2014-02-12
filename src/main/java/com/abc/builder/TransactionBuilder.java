package com.abc.builder;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;

import com.abc.model.Transaction;
import com.abc.model.impl.DepositTransactionImpl;
import com.abc.model.impl.InterestTransactionImpl;
import com.abc.model.impl.WithdrawTransactionImpl;
import com.abc.util.DateProvider;

public class TransactionBuilder {
	@Autowired
	private DateProvider provider;
	
	private double amount;
	private int type;
	private SecureRandom random = new SecureRandom();
	public static final int DEPOSIT = 0;
	public static final int WITHDRAW = 1;
	public static final int INTEREST = 2;
	
	public TransactionBuilder() {
	}
	
    public TransactionBuilder(
       final double amount) 
    {
       this.amount = amount;
    }

    public TransactionBuilder amount(double amount)
    {
       this.amount = amount;
       return this;
    }
    
    public TransactionBuilder type(int type)
    {
       this.type = type;
       return this;
    }
    
    public Transaction createTransaction()
    {
    	switch(type) {
		case TransactionBuilder.DEPOSIT: 
			return new DepositTransactionImpl(this.amount, random.nextLong(), provider.now());
		case TransactionBuilder.WITHDRAW: 
			return new WithdrawTransactionImpl(this.amount, random.nextLong(), provider.now());
		case TransactionBuilder.INTEREST: 
			return new InterestTransactionImpl(this.amount, random.nextLong(), provider.now());
		default: 
			return new InterestTransactionImpl(this.amount, random.nextLong(), provider.now());
	}
    }
}
