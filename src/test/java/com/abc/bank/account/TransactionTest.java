package com.abc.bank.account;


import org.junit.Assert;
import org.junit.Test;

import com.abc.bank.account.Transaction;
import com.abc.bank.account.Transaction.TransactionType;

public class TransactionTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void transactionTest() {
        double amount = 1000;
        Transaction transaction = new Transaction(amount, TransactionType.credit);
        Assert.assertEquals(amount, transaction.getAmount(),DOUBLE_DELTA);
    }
    
    @Test
    public void transactionExecuteCreditTest() {
        double amount = 1000;
        Transaction transaction = new Transaction(1000, TransactionType.credit);
        double result = transaction.execute(amount);
        Assert.assertEquals(2000, result, DOUBLE_DELTA);
    }
    
    @Test
    public void transactionExecuteDebitTest() {
        double amount = 1000;
        Transaction transaction = new Transaction(500, TransactionType.debit);
        double result = transaction.execute(amount);
        Assert.assertEquals(500, result, DOUBLE_DELTA);
    }
}
