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

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
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
        String statement = "Statement for " + name + '\n';
        double total = 0.0;
        for (Account account : accounts) {
            statement += '\n' + statementForAccount(account) + '\n';
            total += account.getBalance();
        }
        statement += "\nTotal In All Accounts " + formatToDollars(total);
        return statement;
    }

    private String statementForAccount(Account account) {
        String statement = "";

        //Translate to pretty account type
        switch (account.getAccountType()) {
            case Account.CHECKING:
                statement += "Checking Account\n";
                break;
            case Account.SAVINGS:
                statement += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                statement += "Maxi Savings Account\n";
                break;
        }

        //Now display all the transactions
        for (Transaction transaction : account.getTransactions()) {
            statement += "  " + (transaction.amount < 0 ? "withdrawal" : "deposit") + ' ' + formatToDollars(transaction.amount) + '\n';
        }
        statement += "Total " + formatToDollars(account.getBalance());
        return statement;
    }

    private static String formatToDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }
}
