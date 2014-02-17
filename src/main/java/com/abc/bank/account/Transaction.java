package com.abc.bank.account;

import java.util.Date;

import com.abc.bank.util.ConversionUtil;
import com.abc.bank.util.DateProvider;

/**
 * Represents a transaction
 *
 */
public class Transaction {
    private final double amount;
    private final Date transactionDate;
    private TransactionType type;

    public enum TransactionType{
        credit,
        debit
    }

    public Transaction(double amount,TransactionType type) {
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
        this.type = type;
    }

    protected double getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
    
    @Override
    public String toString(){
        return ((type == TransactionType.debit ? "withdrawal" : "deposit")
                 + " " + ConversionUtil.toDollars(amount));
    }
    
    /**
     * Executes the transaction on the given
     * amount
     * @param amt
     * @return
     */
    public double execute(double amt){
        double result = amt;
        switch(type){
        case credit:
            result += amount;
            break;
        case debit:
            result -= amount;
            break;
        }
        return result;
    }
}