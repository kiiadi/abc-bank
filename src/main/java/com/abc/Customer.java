package com.abc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Customer {
    private final String name;
    private final Collection<Account> accounts;

    public Customer(String name) {
        this.name = name;
        accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public void openAccount(Account account) {
        accounts.add(account);
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(accounts);
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account account : accounts) {
            total += account.interestEarned();
        }
        return total;
    }
}
