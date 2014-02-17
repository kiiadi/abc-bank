package com.abc.bank.account;

import java.util.LinkedList;

import com.abc.bank.account.Transaction.TransactionType;
import com.abc.bank.exception.InsufficuentFundsException;
import com.abc.bank.util.ConversionUtil;

/**
 * Represents a bank account. Allows clients to deposit, withdraw and get balance 
 * on the account.Implementing classes need to implement the interest behavior based on
 * the account type.
 *
 */
public abstract class Account {

    private LinkedList<Transaction> transactions = new LinkedList<Transaction>();
    public abstract double interestEarned();
    public abstract AccountType getAccountType();
    private volatile double balance = 0.0;
    //TODO: This should be persisted eventually
    private LinkedList<String> transactionHistory = new LinkedList<String>();

    /**
     * Puts the given amount into this account
     * @param amount
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            synchronized (transactions) {
                transactions.add(new Transaction(amount, TransactionType.credit));
            }
        }
    }

    /**
     * Removes and returns the given amount from this account if available
     * @param amount
     * @return
     * @throws InsufficuentFundsException if the amount requested is larger than the
     * balance in this account
     */
    public double withdraw(double amount) throws InsufficuentFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } 
        synchronized (transactions) { // enclose the get and update
            balance = flushTransactions(balance);
            if(balance < amount){
                throw new InsufficuentFundsException("Amount must be less than the current balance: "
                        + balance);
            }
            else {
                transactions.add(new Transaction(amount, TransactionType.debit));
                return amount;
            }
        }
    }

    /**
     * Returns the balance on this account.
     * This is a lazy call, the transaction queue is
     * flushed only when this method is called
     * @return
     */
    public double getBalance() {
        synchronized (transactions) {
            balance = flushTransactions(balance);
        }
        return balance;
    }
    
    /**
     * Processes and empties the transaction queue
     * @param initialAmount
     * @return
     */
    private double flushTransactions(double initialAmount) {
        double result = initialAmount;
        while(!transactions.isEmpty()){
            Transaction t = transactions.remove();
            transactionHistory.add(t.toString());
            result = t.execute(result);
        }
        return result;
    }

    @Override
    public String toString() {
        String s = getAccountType().toString();
        s += "\n";
        s+= getTransactionsAsFormattedString();
        s += "Total " + ConversionUtil.toDollars(getBalance());
        return s;
    }

    private String getTransactionsAsFormattedString() {
        String s = "";
        for (String t : transactionHistory) {
            s += "  " + t + "\n";
        }
        return s;
    }
}