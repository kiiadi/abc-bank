package com.abc.builder;

import java.security.SecureRandom;
import java.util.List;

import com.abc.model.Account;
import com.abc.model.Customer;
import com.abc.model.impl.CustomerImpl;

public class CustomerBuilder {
	private List<Account> accounts;
	private String name;
	private SecureRandom random = new SecureRandom();
	
	public CustomerBuilder() {
	}
	
    public CustomerBuilder(
       final List<Account> accounts) 
    {
       this.accounts = accounts;
    }

    public CustomerBuilder accounts(final List<Account> type)
    {
       this.accounts = type;
       return this;
    }
    
    public CustomerBuilder name(final String type)
    {
       this.name = type;
       return this;
    }

    public Customer createCustomer()
    {
       return new CustomerImpl(
         name, accounts, random.nextLong());
    }
}
