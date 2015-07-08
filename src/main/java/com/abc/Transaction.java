package com.abc;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {
  public enum TransactionType {
    WITHDRAWAL("withdrawal"),
    DEPOSIT("deposit");

    private final String label;

    TransactionType(String label) {

      this.label = label;
    }

    public String getLabel() {
      return label;
    }

  }

  private final double amount;

  private final Date date;
  private final TransactionType type;

  public Transaction(double amount) {
    this.amount = amount;
    this.type = (amount < 0 ? TransactionType.WITHDRAWAL : TransactionType.DEPOSIT);
    this.date = DateProvider.getInstance().now();
  }

  public double getAmount() {
    return amount;
  }

  public Date getDate() {
    return date;
  }

  public TransactionType getType() {
    return type;
  }

  public int compareTo(Transaction that) {
    return this.date.compareTo(that.date);
  }
}
