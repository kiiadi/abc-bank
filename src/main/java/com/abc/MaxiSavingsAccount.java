package com.abc;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 1:34 PM
 */
public class MaxiSavingsAccount extends Account {
  public static final double LOW_RATE = 0.02;
  public static final double MED_RATE = 0.05;
  public static final double HIGH_RATE = 0.1;

  //The balance at which the applied interest rate increases from LOW_RATE to MED_RATE:
  public static final double LOW_MED_BOUNDARY = 1000.0;

  //The balance at which the applied interest rate increases from MED_RATE to HIGH_RATE:
  public static final double MED_HIGH_BOUNDARY = 2000.0;

  //The total LOW_RATE interest accrued on an balance equal to LOW_MED_BOUNDARY
  public static final double TOTAL_LOW_INTEREST = LOW_MED_BOUNDARY * LOW_RATE;

  //The total MED_RATE interest accrued on an balance equal to MED_HIGH_BOUNDARY
  public static final double TOTAL_MED_INTEREST = (MED_HIGH_BOUNDARY - LOW_MED_BOUNDARY) * MED_RATE;

  @Override
  public String getName() {
    return "Maxi Savings Account";
  }

  @Override
  public double interestEarned() {
    double amount = sumTransactions();
    if (amount <= LOW_MED_BOUNDARY) {
      return amount * LOW_RATE;
    } else if (amount <= MED_HIGH_BOUNDARY) {
      return TOTAL_LOW_INTEREST + (amount - LOW_MED_BOUNDARY) * MED_RATE;
    } else {
      return TOTAL_LOW_INTEREST + TOTAL_MED_INTEREST + (amount - MED_HIGH_BOUNDARY) * HIGH_RATE;
    }
  }
}
