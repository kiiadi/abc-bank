package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {

    private final String name;
    private final List<Account> accounts;

    private final Object transferLock = new Object();

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
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

    /**
     * Transfers funds.
     * <p>If parameter <tt>from</tt> is null, the funds are deposited.
     * <p>If parameter <tt>to</tt> is null, the funds are withdrawn.
     * <p>Otherwise the funds are transferred between accounts.
     *
     * @param from   the account from which the funds will be withdrawn
     * @param to     the account that the funds will be deposited to
     * @param amount funds
     * @throws IllegalArgumentException if incorrect accounts are used
     */
    public void transferFunds(Account from, Account to, double amount) {
        if (from == null && to == null) {
            throw new IllegalArgumentException("At least one account should not be null");
        }
        if (from == null) {
            // deposit
            synchronized (transferLock) {
                to.deposit(amount);
            }
        } else if (to == null) {
            // withdraw
            synchronized (transferLock) {
                from.withdraw(amount);
            }
        } else {
            if (!accounts.contains(from) || !accounts.contains(to)) {
                throw new IllegalArgumentException("Customer does not have such accounts");
            }
            // transfer funds between accounts
            synchronized (transferLock) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public String getStatement() {
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

        //Translate to pretty account type
        s += a.getAccountName() + '\n';

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
            total += t.getAmount();
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private static String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }
}
