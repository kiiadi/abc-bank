package com.abc.account;

import com.abc.AbstractTestCase;
import org.junit.Test;

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
}