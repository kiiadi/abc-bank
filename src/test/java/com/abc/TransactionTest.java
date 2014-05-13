package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TransactionTest {
  @Test
  public void testWithdrawalStatementText() {
    Withdrawal withdrawal = new Withdrawal(1234567.89);
    assertEquals("  withdrawal $1,234,567.89\n", withdrawal.getStatementLine());
  }

  @Test
  public void testDepositStatementText() {
    Deposit deposit = new Deposit(1234567.89);
    assertEquals("  deposit $1,234,567.89\n", deposit.getStatementLine());
  }
}
