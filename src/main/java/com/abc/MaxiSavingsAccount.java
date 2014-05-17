package com.abc;

import org.joda.time.DateTime;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 1:34 PM
 */
public class MaxiSavingsAccount extends Account {
  public static final double LOW_RATE = 0.001;
  public static final double HIGH_RATE = 0.05;
  
  public static final int LOW_MED_BOUNDARY_DAYS = 10;

  @Override
  public String getName() {
    return "Maxi Savings Account";
  }

  @Override
  public double interestEarned() {
    double amount = sumTransactions();
    if (getLatestTransactionDate().plusDays(LOW_MED_BOUNDARY_DAYS).isBefore(DateTime.now().withMillisOfDay(0)))
      return amount * HIGH_RATE;
    return amount * LOW_RATE;
  }

}
