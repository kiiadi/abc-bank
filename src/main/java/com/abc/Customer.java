package com.abc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new LinkedList<>();
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
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder("Statement for ").append(name).append("\n");
        double total = 0.0;
        for (Account a : accounts) {
            statement.append("\n").append(a.statementForAccount()).append("\n");
            total += a.sumTransactions();
        }
        statement.append("\n").append("Total In All Accounts ").append(String.format("$%,.2f", total));
        return statement.toString();
    }
}
