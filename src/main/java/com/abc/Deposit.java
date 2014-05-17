package com.abc;

import org.joda.time.DateTime;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 10:26 PM
 */
public class Deposit extends Transaction {
  public Deposit(double amount) {
    super(amount);
  }

  public Deposit(double amount, DateTime transactionTime) {
    super(amount, transactionTime);
  }

  @Override
  String getStatementLine() {
    return String.format("  deposit $%,.2f\n", amount);
  }

  @Override
  public double getAmount() {
    return amount;
  }
}
