package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = Collections.synchronizedList(new ArrayList<Account>());
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public void transfer (Account fromAccount, Account toAccount, double amount) {
    	if (amount <= 0) {
			throw new IllegalArgumentException("amount must be greater than zero");
		} else if (!accounts.contains(fromAccount) || !accounts.contains(toAccount)) {
			throw new IllegalArgumentException("customer can only access their own accounts");
		} else {
			fromAccount.transfer(-amount);
			toAccount.transfer(amount);
		}
    }
    
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += (a.calculateTotal() - a.sumTransactions());
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.calculateTotal();
        }
        statement += "\nTotal In All Accounts " + Utils.toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {

    	String s = a.printName();
        
        for (Transaction t : a.getTransactions()) {
            s += t.print();
        }
        s += "Total " + Utils.toDollars(a.calculateTotal());
        return s;
    }
}
