package com.abc;

import org.joda.time.DateTime;

abstract class Transaction {
  protected final double amount;
  private DateTime transactionDate;

  /**
   * @throws IllegalArgumentException if amount is less than or equal to zero
   */
  public Transaction(double amount) {
    this(amount, DateTime.now());
  }

  public Transaction(double amount, DateTime transactionTime) {
    if(amount <= 0.0)
      throw new IllegalArgumentException("Transaction amount must be greater than zero.");
    this.amount = amount;
    this.transactionDate = transactionTime;
  }

  public DateTime getTransactionDate() {
    return transactionDate;
  }

  abstract String getStatementLine();

  abstract double getAmount();
}
