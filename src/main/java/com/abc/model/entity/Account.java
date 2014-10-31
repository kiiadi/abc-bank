package com.abc.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public abstract class Account {

    private String name;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    protected Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getAccountType();

    public abstract BigDecimal calculateInterest();

    public BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal("0");

        for(Transaction transaction : transactions) {
            balance = balance.add(transaction.toSignedAmount());
        }

        return balance;
    }

}
