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
  String getStatementText() {
    return String.format("deposit $%,.2f", amount);
  }

  @Override
  public double getAmount() {
    return amount;
  }
}
