package com.abc;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 10:26 PM
 */
public class Deposit extends Transaction {
  public Deposit(double amount) {
    super(amount);
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
