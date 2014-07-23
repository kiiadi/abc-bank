package com.abc;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

public class Account {
    public static final String INVALID_AMOUNT_MESSAGE = "amount must be greater than zero";
    private final AccountType accountType;
    private final List<Transaction> transactions = new ArrayList<Transaction>();

    public Account(final AccountType accountType) {
        this.accountType = accountType;
    }

    public void deposit(double amount) {
        add(new Transaction(checkValidAmount(amount)));
    }

    public void withdraw(double amount) {
        add(new Transaction(-checkValidAmount(amount)));
    }

    public double interestEarned() {
        return getAccountType().calculateInterestEarned(currentBalance());
    }

    public double currentBalance() {
        double amount = 0.0;
        for (Transaction transaction : getTransactions())
            amount += transaction.getAmount();
        return amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private double checkValidAmount(double amount) {
        checkArgument(amount > 0, INVALID_AMOUNT_MESSAGE);
        return amount;
    }

    private boolean add(final Transaction transaction) {
        return transactions.add(transaction);
    }
}
