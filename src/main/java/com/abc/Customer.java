package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.accounts.Account;

import static java.lang.Math.abs;

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

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
    	double total = 0;
        for (Account a : accounts) {
        	if (a != null) {
        		total += a.getCurrentAccruedInterest();
        	}
        }
        
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
        	if (a != null) {
	            statement += "\n" + statementForAccount(a) + "\n";
	            total += a.getCurrentAccountBalance();
        	}
        }        
        statement += "\nTotal In All Accounts " + toDollars(total);
        
        return statement;
    }

    private String statementForAccount(Account a) {
        StringBuilder statement = new StringBuilder();

        statement.append(a.getAccountDescription()).append("\n");

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
        	statement.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit").append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }
        
        statement.append("Total ").append(toDollars(total));
        
        return statement.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
