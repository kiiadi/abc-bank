package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for all accounts.
 */
public abstract class Account {

    private final List<Transaction> transactions = new ArrayList<>();

    protected static final DateProvider dateProvider = DateProvider.getInstance();

    /**
     * Deposits funds.
     * This method requires external synchronization.
     *
     * @param amount Funds
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    /**
     * Withdraws funds.
     * This method requires external synchronization.
     *
     * @param amount Funds
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public abstract double interestEarned();

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.getAmount();
        return amount;
    }

    public abstract String getAccountName();

    /**
     * Return a defensive copy of transactions to avoid any external modification of them.
     *
     * @return Transactions
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return this.getAccountName().equals(account.getAccountName());
    }

    @Override
    public int hashCode() {
        return getAccountName().hashCode();
    }
}
