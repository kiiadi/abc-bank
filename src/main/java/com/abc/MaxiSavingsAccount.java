package com.abc;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 1:34 PM
 */
public class MaxiSavingsAccount extends Account {
  private static final double LOWER_MAXI_RATE = 0.02;
  private static final double INTERMEDIATE_MAXI_RATE = 0.05;
  private static final double HIGHER_MAXI_RATE = 0.1;

  @Override
  public String getName() {
    return "Maxi Savings Account";
  }

  @Override
  public double interestEarned() {
    double amount = sumTransactions();
    if (amount <= 1000) {
      return amount * LOWER_MAXI_RATE;
    } else if (amount <= 2000) {
      return 20 + (amount - 1000) * INTERMEDIATE_MAXI_RATE;
    } else {
      return 70 + (amount - 2000) * HIGHER_MAXI_RATE;
    }
  }
}
