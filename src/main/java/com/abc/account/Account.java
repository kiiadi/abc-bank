package com.abc.account;

import com.abc.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Account {
  private final AccountType accountType;
  private final List<Transaction> transactions;

  public Account(AccountType accountType) {
    this.accountType = accountType;
    this.transactions = new ArrayList<Transaction>();
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("amount must be greater than zero");
    }
    else {
      transactions.add(new Transaction(amount));
    }
  }

  public void withdraw(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("amount must be greater than zero");
    }
    else {
      transactions.add(new Transaction(-amount));
    }
  }

  public double interestEarned() {
    double amount = getTotalTransactions();

    AccountInterestCalculator calculator = new AccountInterestCalculator(amount);

    AccountType.Utils.visit(accountType, calculator);

    return calculator.getInterest();
  }

  public double getTotalTransactions() {
    double amount = 0.0;
    for (Transaction transaction : transactions) {
      amount += transaction.getAmount();
    }
    return amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Account account = (Account)o;

    return accountType == account.accountType;

  }
}
