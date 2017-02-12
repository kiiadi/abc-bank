package com.abc;

import java.util.LinkedList;
import java.util.List;

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

    public void openAccount(Account account) {
        accounts.add(account);
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        return accounts.stream().mapToDouble(a -> a.interestEarned()).sum();
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
