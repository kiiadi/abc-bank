package com.abc;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 1:36 PM
 */
public class SavingsAccount extends Account {
  private static final double LOWER_SAVINGS_RATE = 0.001;
  private static final double HIGHER_SAVINGS_RATE = 0.002;

  @Override
  public String getName() {
    return "Savings Account";
  }

  @Override
  public double interestEarned() {
    double amount = sumTransactions();
    if(amount <= 1000) {
      return amount * LOWER_SAVINGS_RATE;
    } else {
      return 1 + (amount - 1000) * HIGHER_SAVINGS_RATE;
    }
  }
}
