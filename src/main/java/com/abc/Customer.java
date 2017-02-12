package com.abc;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public Account openAccount(Account account) {
        accounts.add(account);
        return account ;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        return accounts.stream().mapToDouble(Account::interestEarned).sum();
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder("Statement for ").append(name).append(System.lineSeparator());
        double total = 0.0;
        for (Account a : accounts) {
            statement.append(System.lineSeparator()).append(a.statementForAccount()).append(System.lineSeparator());
            total += a.sumTransactions();
        }
        statement.append(System.lineSeparator()).append("Total In All Accounts ").append(String.format("$%,.2f", total));
        return statement.toString();
    }

    public void transfer(Account a1, Account a2, double amount) {
        Transaction t1 = a1.lastTransaction();
        Transaction t2 = a2.lastTransaction();
        try {
            a1.withdraw(amount);
            a2.deposit(amount);
        } catch (Exception ex) {
            a1.rollback(t1);
            a2.rollback(t2);
            throw ex;
        }
    }
}

