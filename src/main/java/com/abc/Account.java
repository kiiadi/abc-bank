package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

  private List<Transaction> transactions = new ArrayList<Transaction>();

  abstract String getName();

  abstract double interestEarned();

  public void deposit(double amount) {
    transactions.add(new Deposit(amount));
  }

  public void withdraw(double amount) {
    transactions.add(new Withdrawal(amount));
  }

  public double sumTransactions() {
    double amount = 0.0;
    for(Transaction transaction : transactions)
      amount += transaction.getAmount();
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
