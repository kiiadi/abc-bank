package com.abc.account;

import java.util.Collection;

import com.abc.Transaction;

public interface Account {

    String getName();

    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();

    double interestEarned();

    Collection<Transaction> getTransactions();
}
