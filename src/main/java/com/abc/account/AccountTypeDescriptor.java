package com.abc.account;

public class AccountTypeDescriptor implements AccountType.AccountTypeVisitor {
  private String description;

  public void visitChecking() {
    description = "Checking Account";
  }

  public void visitSaving() {
    description = "Savings Account";

  }

  public void visitMaxiSavings() {
    description = "Maxi Savings Account";

  }

  public String getDescription() {
    return description;
  }
}
