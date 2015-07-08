package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TransactionTest extends AbstractTestCase {

  @Test
  public void transaction_date_not_null() {
    Transaction transaction = new Transaction(-5, Transaction.TransactionType.DEPOSIT);

    assertNotNull(transaction.getDate());
  }
}
