package com.abc.account;

import com.abc.AbstractTestCase;
import com.abc.DateProvider;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AccountTest extends AbstractTestCase {

  @Test
  public void account_default_state() {
    Account account = new Account(AccountType.CHECKING);

    assertEquals(0, account.getTransactions().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void account_transaction_deposit_exception() {
    Account account = new Account(AccountType.CHECKING);
    account.deposit(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void account_transaction_withdraw_exception() {
    Account account = new Account(AccountType.CHECKING);
    account.withdraw(-1);
  }

  @Test
  public void account_total_transaction() {
    Account account = new Account(AccountType.CHECKING);
    account.withdraw(20);
    account.deposit(500);

    assertEquals(480, account.getTotalTransactions(), DOUBLE_DELTA);
  }

  @Test
  public void account_interest_earned() {
    Account account = new Account(AccountType.CHECKING);
    account.withdraw(20);
    account.deposit(500);

    assertEquals(0.48, account.interestEarned(), DOUBLE_DELTA);
  }

  @Test
  public void account_interest_earned_on_maxi_savings_when_no_withdraw_in_the_past_10_Days() {
    MockDateProvider provider = new MockDateProvider(-20);
    DateProvider.setInstance(provider);

    Account account = new Account(AccountType.MAXI_SAVINGS);
    account.deposit(1500);
    account.withdraw(500);

    assertEquals(50., account.interestEarned(), DOUBLE_DELTA);
  }

  @Test
  public void account_interest_earned_on_maxi_savings_when_withdraw_in_the_past_10_Days() {
    MockDateProvider provider = new MockDateProvider(-5);
    DateProvider.setInstance(provider);

    Account account = new Account(AccountType.MAXI_SAVINGS);
    account.deposit(1500);
    account.withdraw(500);

    assertEquals(10., account.interestEarned(), DOUBLE_DELTA);
  }

  @Test
  public void account_interest_earned_on_maxi_savings_when_withdraw_in_the_past_10_Days_with_2_transactions() {

    Account account = new Account(AccountType.MAXI_SAVINGS);
    MockDateProvider provider = new MockDateProvider(-20);
    DateProvider.setInstance(provider);
    account.deposit(2000);
    account.withdraw(500);

    provider = new MockDateProvider(-5);
    DateProvider.setInstance(provider);
    account.withdraw(500);

    assertEquals(10., account.interestEarned(), DOUBLE_DELTA);
  }

  private static class MockDateProvider implements DateProvider.Provider {
    private final int days;

    public MockDateProvider(int days) {
      this.days = days;
    }

    public Date now() {
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.DAY_OF_YEAR, days);

      return calendar.getTime();
    }
  }
}