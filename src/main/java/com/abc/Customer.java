package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private final String name;
    private final List<Account> accounts;

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

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account account : accounts) {
            total += account.interestEarned();
        }
        return total;
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder("Statement for ").append(name).append('\n');
        double total = 0.0;
        for (Account account : accounts) {
            statement.append('\n')
                     .append(statementForAccount(account))
                     .append('\n');
            total += account.getBalance();
        }
        statement.append("\nTotal In All Accounts ").append(formatToDollars(total));
        return statement.toString();
    }

    private String statementForAccount(Account account) {
        StringBuilder statement = new StringBuilder();

        //Translate to pretty account type
        switch (account.getAccountType()) {
            case Account.CHECKING:
                statement.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                statement.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                statement.append("Maxi Savings Account\n");
                break;
        }

        //Now display all the transactions
        for (Transaction transaction : account.getTransactions()) {
            statement.append("  ")
                     .append(transaction.amount < 0 ? "withdrawal" : "deposit")
                     .append(' ')
                     .append(formatToDollars(transaction.amount))
                     .append('\n');
        }
        statement.append("Total ").append(formatToDollars(account.getBalance()));
        return statement.toString();
    }

    private static String formatToDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }
}
