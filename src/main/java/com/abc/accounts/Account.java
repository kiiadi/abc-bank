package com.abc.accounts;

import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction;

public abstract class Account {

    public List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > sumTransactions()) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }


    public double sumTransactions() {
        return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.amount;
        return amount;
    }
    
    boolean hasWithdrawlInLast10Days() {
        //TODO: quick and dirty - does not take into account midnight. fix later.
        long inLast10Days = System.currentTimeMillis() - 86_400_000 * 10;
        return transactions.stream().filter( t -> (t.isWithdrawl() && t.getTransactionDate().getTime() > inLast10Days)).count() > 0;
    }

    public abstract String accountTypeString();
    public abstract double interestEarned();

}
