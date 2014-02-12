package com.abc.builder;

import com.abc.model.Account;
import com.abc.model.Statement;
import com.abc.model.impl.StatementImpl;

public class StatementBuilder {
	private double total;
	private Account account;
	
	public StatementBuilder() {
	}
	
    public StatementBuilder(final Account act,
       final double total) 
    {
    	this.account = act;
       this.total = total;
    }

    public StatementBuilder total(double amount)
    {
       this.total = amount;
       return this;
    }
    
    public StatementBuilder account(Account amount)
    {
       this.account = amount;
       return this;
    }
    
    public Statement createStatement()
    {
       return new StatementImpl(this.account,
         this.total);
    }
}
