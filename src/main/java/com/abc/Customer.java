package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private String name;
    public String getName() { return name; }
    
    private List<Account> accounts;
    public List<Account> getAccounts() { return Collections.unmodifiableList(accounts);}

	public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
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
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
    	return Reports.customerStatement(this);
    }
}
