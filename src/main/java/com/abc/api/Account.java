package com.abc.api;

import java.math.BigDecimal;

public interface Account {
    AccountId getAccountId();
    CustomerId getCustomerId();
    void addTransaction(Transaction tx);
    BigDecimal averageDailyBalance();
    AccountStatement getAccountStatement();



}
