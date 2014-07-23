package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Account> accounts;

    public Customer(final String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public double totalBalance(){
        double total = 0.0;
        for (Account account : accounts)
            total += account.currentBalance();

        return total;
    }

    public void transfer(final Account from, final Account to, final double amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }

    public Account open(final AccountType accountType) {
        Account account = new Account(accountType);
        accounts.add(account);
        return account;
    }

}
