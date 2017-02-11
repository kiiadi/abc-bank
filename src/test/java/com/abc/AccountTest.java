package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private static final double DOUBLE_DELTA = 1e-15;


    @Test
    public void checking_interestEarned() {
        Account checking = Account.newChecking();

        checking.deposit(1000);
        assertEquals(1, checking.interestEarned(), DOUBLE_DELTA);
        checking.withdraw(600);
        assertEquals(0.4, checking.interestEarned(), DOUBLE_DELTA);
        checking.deposit(1000);
        assertEquals(1.4, checking.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void savings_interestEarned() {
        Account saving = Account.newSavings();

        saving.deposit(1000);
        assertEquals(1, saving.interestEarned(), DOUBLE_DELTA);
        saving.withdraw(600);
        assertEquals(0.4, saving.interestEarned(), DOUBLE_DELTA);
        saving.deposit(1000);
        assertEquals(1.8, saving.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSaving_interestEarned() {
        Account maxiSaving = Account.newMaxiSavings();

        maxiSaving.deposit(1000);
        assertEquals(20, maxiSaving.interestEarned(), DOUBLE_DELTA);
        maxiSaving.withdraw(600);
        assertEquals(8, maxiSaving.interestEarned(), DOUBLE_DELTA);
        maxiSaving.deposit(1000);
        assertEquals(40, maxiSaving.interestEarned(), DOUBLE_DELTA);
        maxiSaving.deposit(10000);
        assertEquals(1010, maxiSaving.interestEarned(), DOUBLE_DELTA);
    }


    @Test
    public void checking_sumTransactions() {
        Account checking = Account.newChecking();

        checking.deposit(1000);
        assertEquals(checking.sumTransactions(), 1000, DOUBLE_DELTA);
        checking.withdraw(600);
        assertEquals(checking.sumTransactions(), 400, DOUBLE_DELTA);
    }

    @Test
    public void saving_sumTransactions() {
        Account saving = Account.newSavings();

        saving.deposit(1000);
        assertEquals(saving.sumTransactions(), 1000, DOUBLE_DELTA);
        saving.withdraw(600);
        assertEquals(saving.sumTransactions(), 400, DOUBLE_DELTA);
    }

    @Test
    public void maxiSaving_sumTransactions() {
        Account maxiSaving = Account.newMaxiSavings();

        maxiSaving.deposit(1000);
        assertEquals(maxiSaving.sumTransactions(), 1000, DOUBLE_DELTA);
        maxiSaving.withdraw(600);
        assertEquals(maxiSaving.sumTransactions(), 400, DOUBLE_DELTA);
    }


}