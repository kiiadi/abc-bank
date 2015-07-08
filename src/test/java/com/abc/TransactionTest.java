package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionTest extends AbstractTestCase {
  @Test
  public void transaction_type_deposit() {
    Transaction transaction = new Transaction(5);

    assertEquals(Transaction.TransactionType.DEPOSIT, transaction.getType());
  }

  @Test
  public void transaction_type_withdrawal() {
    Transaction transaction = new Transaction(-5);

    assertEquals(Transaction.TransactionType.WITHDRAWAL, transaction.getType());
  }

  @Test
  public void transaction_date_not_null() {
    Transaction transaction = new Transaction(-5);

    assertNotNull(transaction.getDate());
  }
}
