package com.abc.impl;

import com.abc.api.AccountId;
import com.abc.api.Transaction;
import com.abc.services.CalendarService;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionImpl implements Transaction {

    private AccountId accountId;
    private Date date;
    private BigDecimal amount;
    private CalendarService calendarService;

    public TransactionImpl(AccountId accountId, BigDecimal amount, CalendarService calendarService) {
        this.accountId = accountId;
        this.amount = amount;
        this.calendarService = calendarService;
        this.date = calendarService.getNow();
    }

    @Override
    public AccountId getAccountId() {
        return accountId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }
}
