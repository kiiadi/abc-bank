package com.abc.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    public void creditAccount(BigDecimal amount) {
        Transaction transaction = new Transaction(amount, Transaction.Type.CREDIT, new Date());
        transactions.add(transaction);
    }

    public void debitAccount(BigDecimal amount) {
        Transaction transaction = new Transaction(amount, Transaction.Type.DEBIT, new Date());
        transactions.add(transaction);
    }

    public void addInterest(BigDecimal amount) {
        Transaction transaction = new Transaction(amount, Transaction.Type.INTEREST, new Date());
        transactions.add(transaction);
    }

    public BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal("0");

        for(Transaction transaction : transactions) {
            balance = balance.add(transaction.toSignedAmount());
        }

        return balance;
    }

    public BigDecimal getTotalInterestReceived() {
        BigDecimal totalInterestReceived = new BigDecimal("0");

        for(Transaction transaction : transactions) {
            if(transaction.getType().equals(Transaction.Type.INTEREST)) {
                totalInterestReceived = totalInterestReceived.add(transaction.toSignedAmount());
            }
        }

        return totalInterestReceived;
    }

    public boolean isThereEnoughMoneyToDebit(BigDecimal amountToDebit) {
        return getBalance().compareTo(amountToDebit) >= 0;
    }

}
