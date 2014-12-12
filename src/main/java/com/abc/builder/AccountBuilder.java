package com.abc.builder;

import java.security.SecureRandom;
import java.util.List;

import com.abc.model.Account;
import com.abc.model.Transaction;
import com.abc.model.impl.CheckingAccountImpl;
import com.abc.model.impl.MaxiSavingsAccountImpl;
import com.abc.model.impl.SavingsAccountImpl;

public class AccountBuilder {
	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;
	private List<Transaction> transactions;
	private SecureRandom random = new SecureRandom();
	private int type = 0;
	private String name;
	
	public AccountBuilder() {
		
	}
	
    public AccountBuilder(int type,
       final List<Transaction> transactions) 
    {
    	this.transactions = transactions;
    }

    public AccountBuilder type(final int type)
    {
       this.type = type;
       return this;
    }
    
    public AccountBuilder name(final String type)
    {
       this.name = type;
       return this;
    }

    public AccountBuilder transactions(final List<Transaction> trans)
    {
       this.transactions = trans;
       return this;
    }

    public Account createAccount()
    {
    	switch(type) {
    		case AccountBuilder.CHECKING: 
    			return new CheckingAccountImpl(this.transactions, random.nextLong(), this.name);
    		case AccountBuilder.SAVINGS: 
    			return new SavingsAccountImpl(this.transactions, random.nextLong(), this.name);
    		case AccountBuilder.MAXI_SAVINGS: 
    			return new MaxiSavingsAccountImpl(this.transactions, random.nextLong(), this.name);
    		default: 
    			return new CheckingAccountImpl(this.transactions, random.nextLong(), this.name);
    	}
    		
    }
}
