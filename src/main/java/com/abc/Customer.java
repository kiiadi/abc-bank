package com.abc;

import java.math.BigDecimal;
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
	
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BigDecimal totalInterestEarned() {
    	BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts)
        	total = total.add(a.interestEarned());
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total = total.add(a.sumTransactions());
        }
        statement += "\nTotal In All Accounts " + Money.dollars(total);
        return statement;
    }

    public String statementForAccount(Account a) {
        String s = a.getClass().getSimpleName() + "\n";
        //Now total up all the transactions
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.getAmount().compareTo(BigDecimal.ZERO) == -1 ? "withdrawal" : "deposit") + " " + Money.dollars(t.getAmount().abs()) + "\n";
            total =  total.add(t.getAmount());
        }
        s += "Total " + Money.dollars(total);
        return s;
    }
    
    

}
