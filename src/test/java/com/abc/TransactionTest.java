package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TransactionTest {
  private static final double DOUBLE_DELTA = 1e-15;

  @Test
  public void testWithdrawalStatementLine() {
    Transaction transaction = new Withdrawal(1234567.89);
    assertEquals("  withdrawal $1,234,567.89\n", transaction.getStatementLine());
  }

  @Test
  public void testDepositStatementLine() {
    Transaction transaction = new Deposit(1234567.89);
    assertEquals("  deposit $1,234,567.89\n", transaction.getStatementLine());
  }

  @Test
  public void testWithdrawalAmount() {
    Transaction transaction = new Withdrawal(1234567.89);
    assertEquals(-1234567.89, transaction.getAmount(), DOUBLE_DELTA);
  }

  @Test
  public void testDepositAmount() {
    Transaction transaction = new Deposit(1234567.89);
    assertEquals(1234567.89, transaction.getAmount(), DOUBLE_DELTA);
  }

  @Test
  public void testNegativeAmount() {
    try {
      new Withdrawal(-1234567.89);
    } catch(IllegalArgumentException exception) {
      assertEquals("Transaction amount must be greater than zero.", exception.getMessage());
    }
  }
}
