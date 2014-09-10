package com.abc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts =  Collections.synchronizedList(new ArrayList<Account>());
    }
    
    public Customer(String name, List<Account> list) {
    	this.name = name ;
    	this.accounts = Collections.synchronizedList(list);
    }

    public String getName() {
        return name;
    }

    public  synchronized void openAccount(Account account) {
        accounts.add(account);
    }
    
    public Account openAccount(AccountType type) throws InvalidAccount {
    	Account acct = AccountFactory.newAccount(type) ;
    	this.openAccount(AccountFactory.newAccount(type));
    	return acct ;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public synchronized double totalInterestEarned() {
        double total = 0;
        for (InterestPayable acct : accounts)
            total += acct.interestEarned();
        return total;
    }
    
    public synchronized double totalBalance() {
    	double total = 0.0 ;
    	for ( Account acct : accounts ) {
    		total += acct.getCurrBalance() ;
    	}
    	return total ;
    }
    
    public List<Account> accounts() {
    	return new ArrayList<Account>(accounts) ;
    }
    
    public static class Builder {
    	String name ;
    	List<Account> accts = new ArrayList<Account>() ;
    	
    	public Builder(String name) {
    		this.name = name ;
    	}
    	
    	public Builder add(Account acct) {
    		accts.add(acct);
    		return this;
    	}
    	
    	public Builder add(AccountType type) throws InvalidAccount {
    		return add(AccountFactory.newAccount(type)) ;
    	}
    	
    	public Customer build() {
    		return new Customer(name, accts) ;
    	}
    }

}
