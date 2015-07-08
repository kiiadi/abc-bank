package com.abc.account;

public class AccountInterestCalculator implements AccountType.AccountTypeVisitor {
  private final double amount;
  private double interest;

  public AccountInterestCalculator(double amount) {
    this.amount = amount;
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
    if (amount <= 1000) {
      interest = amount * 0.02;

    }
    else if (amount <= 2000) {
      interest = 20 + (amount - 1000) * 0.05;
    }
    else {
      interest = 70 + (amount - 2000) * 0.1;
    }
  }

  public double getInterest() {
    return interest;
  }
}
