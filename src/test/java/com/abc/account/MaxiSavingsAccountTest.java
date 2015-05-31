package com.abc.account;

import java.util.Calendar;
import java.util.Date;

import com.abc.transaction.DateProvider;
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
        DateProvider.setInstance(new FrozenDateProvider(daysAgo(11)));
        account.deposit(4000.0);
        account.withdraw(1000.0);
        assertThat(account.interestEarned(), equalTo(150.0));
    }

    private Date daysAgo(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -days);
        return cal.getTime();
    }

    private static class FrozenDateProvider extends DateProvider {

        private final Date date;

        private FrozenDateProvider(Date date) {
            this.date = date;
        }

        @Override
        public Date now() {
            return date;
        }
    }
}
