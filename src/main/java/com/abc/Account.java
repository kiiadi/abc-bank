package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {

    private final Integer accountNumber;
    private final AccountType accountType;
    public List<Transaction> transactions;
    private BigDecimal currentBalance;
    private Random random = new Random();


    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.currentBalance = BigDecimal.ZERO;
        this.accountNumber = randomAccountNo();
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, TransactionType.CREDIT));
            currentBalance = currentBalance.add(amount);
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, TransactionType.DEBIT));
            currentBalance = currentBalance.subtract(amount);


        }
    }

    public Integer getAccountNumber() {
        return this.accountNumber;
    }

    private int randomAccountNo() {
        return random.nextInt(Integer.MAX_VALUE);
    }

    public BigDecimal interestEarned() {
        return accountType.interestEarned(currentBalance);
    }

    public BigDecimal getCurrentBalance() {
        return CurrencyUtil.convertToCurrency(this.currentBalance);
    }

    public AccountType getAccountType() {
        return accountType;
    }

}
