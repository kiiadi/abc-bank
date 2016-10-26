package com.abc;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }
    
    public void transfer(double amount, Account from, Account to) {
    	if(accounts.contains(from) && accounts.contains(to) && from != to) {
    		from.withdraw(amount);
    		to.deposit(amount);
    	} else {
    		throw new IllegalArgumentException("Both accounts must belong to the same customer, and cannot be same.");
    	}
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + a.statementForAccount() + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + String.format("$%,.2f", abs(total));
        return statement;
    }
}
