package com.abc;

import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class AccountTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableTransactionList() {
        Account account = new Account(AccountType.Checking);
        account.deposit(BigDecimal.ONE);
        List<Transaction> transactions = account.getTransactions();
        transactions.add(new Transaction(new BigDecimal(2)));
    }

    @Test
    public void testDeposit() {
        Account account = new Account(AccountType.Checking);
        account.deposit(BigDecimal.ONE);
        account.deposit(new BigDecimal(2));
        List<Transaction> transactions = account.getTransactions();
        Assert.assertEquals(2, transactions.size());
        TestUtils.assertEquals(BigDecimal.ONE, transactions.get(0).getAmount());
        TestUtils.assertEquals(new BigDecimal(2), transactions.get(1).getAmount());
    }

    @Test
    public void testWithdrawals() {
        Account account = new Account(AccountType.Checking);
        account.withdraw(BigDecimal.ONE);
        account.withdraw(new BigDecimal(2));
        List<Transaction> transactions = account.getTransactions();
        Assert.assertEquals(2, transactions.size());
        TestUtils.assertEquals(BigDecimal.ONE.negate(), transactions.get(0).getAmount());
        TestUtils.assertEquals(new BigDecimal(2).negate(), transactions.get(1).getAmount());
    }

    @Test
    public void testSumTransactions() {
        Account account = new Account(AccountType.Checking);
        account.deposit(new BigDecimal(10));
        account.withdraw(new BigDecimal(8));
        TestUtils.assertEquals(new BigDecimal(2), account.sumTransactions());
    }


}
