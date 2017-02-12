package com.abc;

import org.junit.Test;

import static com.abc.AccountType.CHECKING;
import static com.abc.AccountType.MAXI_SAVINGS;
import static com.abc.AccountType.SAVINGS;
import static org.junit.Assert.assertEquals;

public class AccountTest extends BaseTestFixture {

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
        assertEquals(1000, checking.sumTransactions(), DOUBLE_DELTA);
        checking.withdraw(600);
        assertEquals(400, checking.sumTransactions(), DOUBLE_DELTA);
    }

    @Test
    public void saving_sumTransactions() {
        Account saving = Account.newSavings();

        saving.deposit(1000);
        assertEquals(1000, saving.sumTransactions(), DOUBLE_DELTA);
        saving.withdraw(600);
        assertEquals(400, saving.sumTransactions(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSaving_sumTransactions() {
        Account maxiSaving = Account.newMaxiSavings();

        maxiSaving.deposit(1000);
        assertEquals(1000, maxiSaving.sumTransactions(), DOUBLE_DELTA);
        maxiSaving.withdraw(600);
        assertEquals(400, maxiSaving.sumTransactions(), DOUBLE_DELTA);
    }

    @Test
    public void accountType() {
        Account checking = Account.newChecking();
        assertEquals(CHECKING,checking.getAccountType());
        Account saving = Account.newSavings();
        assertEquals(SAVINGS,saving.getAccountType());
        Account maxiSaving = Account.newMaxiSavings();
        assertEquals(MAXI_SAVINGS,maxiSaving.getAccountType());
    }


}