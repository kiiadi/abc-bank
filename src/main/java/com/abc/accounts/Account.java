package com.abc.accounts;

public interface Account {

    void withdraw(double amount);
    void deposit(double amount);

    double interestEarned();
    String getAccountDescription();

    String getStatement();

    double sumTransactions();

    void transferTo(Account toAccount, double amount);
}
