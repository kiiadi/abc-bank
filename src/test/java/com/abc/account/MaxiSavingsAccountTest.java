package com.abc.account;

import java.util.Date;

import com.abc.transaction.DefaultDateProvider;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MaxiSavingsAccountTest extends AccountTest {

    @Override
    protected Account createAccount() {
        return new MaxiSavingsAccount();
    }

    @Test
    public void interestEarned() {
        account.deposit(3000.0);
        assertThat(account.interestEarned(), equalTo(150.0));
    }

    @Test
    public void interestEarnedWithWithdrawalWithin10Days() {
        account.deposit(4000.0);
        account.withdraw(1000.0);
        assertThat(account.interestEarned(), equalTo(3.0));
    }

    @Test
    public void interestEarnedWithWithdrawalOver10Days() {
        Account account = new MaxiSavingsAccount(new FrozenDateProvider(daysInFuture(10)));
        account.deposit(4000.0);
        account.withdraw(1000.0);
        assertThat(account.interestEarned(), equalTo(150.0));
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
