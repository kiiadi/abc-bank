package com.abc;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 1:36 PM
 */
public class SavingsAccount extends Account {
  private static final double LOW_RATE = 0.001;
  private static final double HIGH_RATE = 0.002;

  //The balance at which the applied interest rate increases from LOW_RATE to HIGH_RATE
  private static final double LOW_HIGH_BOUNDARY = 1000.0;

  //The total LOW_RATE interest accrued on an balance equal to LOW_HIGH_BOUNDARY
  private static final double TOTAL_LOW_INTEREST = LOW_HIGH_BOUNDARY * LOW_RATE;

  @Override
  public String getName() {
    return "Savings Account";
  }

  @Override
  public double interestEarned() {
    double amount = sumTransactions();
    if(amount <= LOW_HIGH_BOUNDARY) {
      return amount * LOW_RATE;
    } else {
      return TOTAL_LOW_INTEREST + (amount - LOW_HIGH_BOUNDARY) * HIGH_RATE;
    }
  }
}
