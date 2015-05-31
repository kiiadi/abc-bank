package com.abc;

import java.util.Collection;

public interface Account {

    String getName();

    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();

    double interestEarned();

    Collection<Transaction> getTransactions();
}
