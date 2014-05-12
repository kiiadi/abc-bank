package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TransactionTest {
  @Test
  public void testWithdrawalStatementText() {
    Transaction transaction = new Transaction(-1234567.89);
    assertEquals("withdrawal $1,234,567.89", transaction.getStatementText());
  }

  @Test
  public void testDepositStatementText() {
    Transaction transaction = new Transaction(1234567.89);
    assertEquals("deposit $1,234,567.89", transaction.getStatementText());
  }
}
