package com.abc.transaction.impl;

/**
 * Created by Archana on 9/14/14.
 */

import com.abc.transaction.Transaction;
import com.abc.util.DateProvider;

import java.math.BigDecimal;
import java.util.Date;

public final class SimpleTransaction implements Transaction {
  private final BigDecimal amount;

  private final Date transactionDate;

  public SimpleTransaction(BigDecimal amount) {
    this.amount = amount;
    this.transactionDate = DateProvider.INSTANCE.now();
  }

  public SimpleTransaction(BigDecimal amount, Date transactionDate) {
    this.amount = amount;
    this.transactionDate = transactionDate;
  }
  @Override
  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public Date getTransactionDate() {
    return transactionDate;
  }

}