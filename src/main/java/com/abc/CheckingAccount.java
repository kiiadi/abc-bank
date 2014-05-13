package com.abc;


/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 1:26 PM
 */
public class CheckingAccount extends Account {
  public static final double INTEREST_RATE = 0.001;

  @Override
  public String getName() {
    return "Checking Account";
  }

  @Override
  public double interestEarned() {
    return sumTransactions() * INTEREST_RATE;
  }
}
