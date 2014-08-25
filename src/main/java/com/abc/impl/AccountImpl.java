package com.abc.impl;

import com.abc.api.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class AccountImpl implements Account {

    private AccountType accountType;
    private InterestCalculator interestCalculator;
    private AccountId accountId;
    private CustomerId customerId;
    private List<Transaction> transactions = new LinkedList<>();

    @Override
    public AccountId getAccountId() {
        return null;
    }

    @Override
    public CustomerId getCustomerId() {
        return null;
    }

    @Override
    public void addTransaction(Transaction tx) {
    }

    @Override
    public BigDecimal averageDailyBalance() {
        return null;
    }

    @Override
    public AccountStatement getAccountStatement() {
        List<Transaction> copy = new LinkedList<>(transactions);
        return null;
    }

    void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    void setInterestCalculator(InterestCalculator interestCalculator) {
        this.interestCalculator = interestCalculator;
    }

    void setAccountId(AccountId accountId) {
        this.accountId = accountId;
    }

    void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "accountType=" + accountType +
                ", interestCalculator=" + interestCalculator +
                ", accountId=" + accountId +
                ", customerId=" + customerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountImpl account = (AccountImpl) o;

        if (!accountId.equals(account.accountId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return accountId.hashCode();
    }
}
