package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

  public List<Transaction> transactions = new ArrayList<Transaction>();

  abstract String getName();

  abstract double interestEarned();

  public void deposit(double amount) {
    if(amount <= 0) {
      throw new IllegalArgumentException("amount must be greater than zero");
    } else {
      transactions.add(new Transaction(amount));
    }
  }

  public void withdraw(double amount) {
    if(amount <= 0) {
      throw new IllegalArgumentException("amount must be greater than zero");
    } else {
      transactions.add(new Transaction(-amount));
    }
  }

  public double sumTransactions() {
    double amount = 0.0;
    for(Transaction transaction : transactions)
      amount += transaction.amount;
    return amount;
  }

  public String getStatementText() {
      String statement = String.format("%s\n", getName());

      for(Transaction transaction : transactions)
        statement += "  " + transaction.getStatementText() + "\n";

      statement += "Total " + Util.toDollars(sumTransactions());
      return statement;
  }
}
