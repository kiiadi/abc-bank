package com.abc;

import com.abc.accounts.Account;
import com.abc.util.ReportFormatterHelper;

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

    public void openAccount(Account account) {
        accounts.add(account);
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
        for (Account account : accounts) {
            statement .append("\n").append(account.getStatement()).append("\n");
            total += account.sumTransactions();
        }
        statement .append("\nTotal In All Accounts ").append(ReportFormatterHelper.toDollars(total));
        return statement.toString();
    }

}
