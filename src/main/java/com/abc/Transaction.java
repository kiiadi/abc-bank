package com.abc;

import static java.lang.Math.abs;

public class Transaction {
  public final double amount;

  public Transaction(double amount) {
    this.amount = amount;
  }

  public String getStatementText() {
    if (amount < 0)
      return String.format("withdrawal $%,.2f", abs(amount));
    else
      return String.format("deposit $%,.2f", abs(amount));
  }
}
