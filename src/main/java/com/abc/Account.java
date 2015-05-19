package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {

    private final Lock lock = new ReentrantLock();

    public List<Transaction> transactions = new ArrayList<>();
    private BigDecimal balance = BigDecimal.ZERO;

    /**
     * deposit amount to this account
     *
     * @param amount
     * @throws IllegalArgumentException
     */
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }

        lock.lock();
        try {
            balance = balance.add(amount);
            transactions.add(new Transaction(amount));
        } finally {
            lock.unlock();
        }
    }

    /**
     * withdraws amount from this account
     *
     * @param amount
     * @throws IllegalArgumentException, InsufficientBalanceException
     */
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }

        lock.lock();
        try {
            if (balance.compareTo(amount) < 0) {
                throw new InsufficientBalanceException("account balance=" + balance + " must be greater than withdraw amount=" + amount);
            }
            balance = balance.subtract(amount);
            transactions.add(new Transaction(amount.negate()));
        } finally {
            lock.unlock();
        }
    }

    /**
     * transfers amount from this account to another account
     *
     * @param amount    amount to be transferred
     * @param toAccount account to which the amount will be transferred
     * @return true if transfer was successful; false otherwise
     */
    public boolean transfer(BigDecimal amount, Account toAccount) {
        long startTime = System.nanoTime();
        while (true) {
            if (lock.tryLock()) {
                try {
                    if (toAccount.lock.tryLock()) {
                        try {
                            withdraw(amount);
                            toAccount.deposit(amount);
                        } finally {
                            toAccount.lock.unlock();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
            if (System.nanoTime() - startTime > 5000)
                return false;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * find last withdrawal transaction
     *
     * @return last withdrawal transaction if any
     */
    public Transaction findLastWithdrawal() {
        lock.lock();
        try {
            int i = transactions.size();
            while (i > 0) {
                if (transactions.get(i-1).getType() == TransactionType.withdraw) {
                    return transactions.get(i-1);
                }
                i--;
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    /**
     * sum all transactions and get current balance
     *
     * @return balance
     */
    public BigDecimal sumTransactions() {
        BigDecimal amount = BigDecimal.ZERO;
        lock.lock();
        try {
            for (Transaction t : transactions) {
                amount = amount.add(t.getAmount());
            }
        } finally {
            lock.unlock();
        }
        return amount;
    }

    public BigDecimal getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public abstract BigDecimal interestEarned();

    public abstract String getLabel();

}
