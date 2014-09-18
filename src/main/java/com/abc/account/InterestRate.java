package com.abc.account;

/**
 * Created by Archana on 9/15/14.
 */
public enum InterestRate {
  CHECKING(.2), SAVINGS(.5), MAXI_SAVINGS_MAX(2), MAXI_SAVINGS_MIN(.1);
  private double rate;
  InterestRate(double v) {
    rate = v;
  }

  public double getRate() {
    return rate;
  }}
