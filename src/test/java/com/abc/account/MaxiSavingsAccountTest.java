package com.abc.account;

import java.util.Date;

import com.abc.transaction.DefaultDateProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxiSavingsAccountTest extends AccountTest {

    private static final double DOUBLE_DELTA = 1.0e-15;

    @Override
    protected Account createAccount() {
        return new MaxiSavingsAccount();
    }

    @Test
    public void interestEarned() {
        account.deposit(3000.0);
        assertEquals(150.0, account.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestEarnedWithWithdrawalWithin10Days() {
        account.deposit(4000.0);
        account.withdraw(1000.0);
        assertEquals(3.0, account.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void interestEarnedWithWithdrawalOver10Days() {
        Account account = new MaxiSavingsAccount(new FrozenDateProvider(daysInFuture(11)));
        account.deposit(4000.0);
        account.withdraw(1000.0);
        assertEquals(150.0, account.interestEarned(), DOUBLE_DELTA);
    }

    private Date daysInFuture(int days) {
        return new DefaultDateProvider().now(days);
    }

    private static class FrozenDateProvider extends DefaultDateProvider {

        private final Date date;

        private FrozenDateProvider(Date date) {
            this.date = date;
        }

        public Date now() {
            return date;
        }
    }
}
