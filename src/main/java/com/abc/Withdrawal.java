package com.abc;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 10:18 PM
 */
public class Withdrawal extends Transaction {
  public Withdrawal(double amount) {
    super(amount);
  }

  @Override
  String getStatementText() {
    return String.format("withdrawal $%,.2f", amount);
  }

  @Override
  public double getAmount() {
    return -amount;
  }
}
