package com.abc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractAccount implements Account {

    private final String name;
    private final Collection<Transaction> transactions;

    protected AbstractAccount(String name) {
        this.name = name;
        transactions = new ArrayList<Transaction>();
    }

    public String getName() {
        return name;
    }

    public void deposit(double amount) {
        validate(amount);
        transactions.add(new Transaction(amount));
    }

    public void withdraw(double amount) {
        validate(amount);
        transactions.add(new Transaction(-amount));
    }

    private static void validate(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
    }

    public double getBalance() {
        double total = 0.0;
        for (Transaction transaction : transactions) {
            total += transaction.amount;
        }
        return total;
    }

    public Collection<Transaction> getTransactions() {
        return Collections.unmodifiableCollection(transactions);
    }
}
