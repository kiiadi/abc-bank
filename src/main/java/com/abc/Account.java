package com.abc;

import java.util.LinkedList;
import java.util.List;

public class Account {
    private AccountType accountType;
    private double balance;
    private List<Transaction> transactions = new LinkedList<Transaction>();

    Account(AccountType accType, Double balance) {
        this.accountType = accType;
        this.balance = balance;

    }

    void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit Amount should be positive non zero");
        balance += amount;
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw Amount should be positive non zero");
        balance -= amount;
        transactions.add(new Transaction(TransactionType.WITHDRAW, amount));
    }

    void transfer(double amount, Account toAccount) {
        if (amount <= 0) throw new IllegalArgumentException("Transfer Amount should be positive non zero");
        this.withdraw(amount);
        toAccount.deposit(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAW, amount));
    }

    protected double getInterestEarned() {
        return accountType.calculateInterest(balance);

    }

    private void printTransactions() {

        for (Transaction transaction : transactions) {
            System.out.println(transaction);

        }

    }

    private void printBalanceAmount() {

        System.out.println(getBalance());


    }


    public void getStatement() {
        printTransactions();
        printBalanceAmount();
    }

    public double getBalance() {
        return balance;
    }

    AccountType getAccountType() {
        return accountType;
    }
}
