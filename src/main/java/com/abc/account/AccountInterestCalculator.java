package com.abc.account;

public class AccountInterestCalculator implements AccountType.AccountTypeVisitor {
  private final double amount;
  private final boolean noWithdrawInThePast10Days;
  private double interest;

  public AccountInterestCalculator(double amount, boolean noWithdrawInThePast10Days) {
    this.amount = amount;
    this.noWithdrawInThePast10Days = noWithdrawInThePast10Days;
  }

  public AccountInterestCalculator(double amount) {
    this(amount, false);
  }

  public void visitChecking() {
    interest = amount * 0.001;
  }

  public void visitSaving() {
    if (amount <= 1000) {
      interest = amount * 0.001;
    }
    else {
      interest = 1 + (amount - 1000) * 0.002;
    }
  }

  public void visitMaxiSavings() {
    if (noWithdrawInThePast10Days) {
      interest = amount * 0.05;
    }
    else {
      interest = amount * 0.01;
    }
  }

  public double getInterest() {
    return interest;
  }
}
