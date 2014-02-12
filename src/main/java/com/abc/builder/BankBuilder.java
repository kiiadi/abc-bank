package com.abc.builder;

import java.security.SecureRandom;
import java.util.List;

import com.abc.model.Bank;
import com.abc.model.Customer;
import com.abc.model.impl.BankImpl;

public class BankBuilder {
	private List<Customer> accounts;
	private SecureRandom random = new SecureRandom();
	
	public BankBuilder() {
	}
	
    public BankBuilder(
       final List<Customer> accounts) 
    {
       this.accounts = accounts;
    }

    public BankBuilder customers(final List<Customer> type)
    {
       this.accounts = type;
       return this;
    }

    public Bank createBank()
    {
       return new BankImpl(
          accounts, random.nextLong());
    }
}
