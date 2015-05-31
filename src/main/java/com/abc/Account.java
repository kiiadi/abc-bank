package com.abc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    private final List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public double interestEarned() {
        double amount = getBalance();
        switch (accountType) {
            case SAVINGS:
                if (amount <= 1000) {
                    return amount * 0.001;
                } else {
                    return 1 + (amount - 1000) * 0.002;
                }
            case MAXI_SAVINGS:
                if (amount <= 1000) {
                    return amount * 0.02;
                }
                if (amount <= 2000) {
                    return 20 + (amount - 1000) * 0.05;
                }
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }

    public Collection<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public double getBalance() {
        double total = 0.0;
        for (Transaction transaction : transactions) {
            total += transaction.amount;
        }
        return total;
    }

    public int getAccountType() {
        return accountType;
    }
}
