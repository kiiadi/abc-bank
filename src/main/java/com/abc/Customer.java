package com.abc;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.abc.utils.BankUtils;

public class Customer {
    private String name;
    private List<Account> accounts;
    private ReentrantReadWriteLock accountsLock = new ReentrantReadWriteLock();

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
    	try {
    		accountsLock.writeLock().lock();
    		accounts.add(account);
    	} finally {
    		accountsLock.writeLock().unlock();
    	}
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
    	try {
    		accountsLock.readLock().lock();
	        for (Account a : accounts)
	            total += a.interestEarned();
    	} finally {
    		accountsLock.readLock().unlock();
    	}
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
    	try {
    		accountsLock.readLock().lock();
	        for (Account a : accounts) {
	            statement += "\n" + a.statementForAccount() + "\n";
	            total += a.sumTransactions();
	        }
    	} finally {
    		accountsLock.readLock().unlock();
    	}
        statement += "\nTotal In All Accounts " + BankUtils.toDollars(total);
        return statement;
    }
}
