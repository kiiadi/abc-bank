package com.abc;

import java.util.*;

import org.joda.time.DateTime;

public abstract class Account {

  private List<Transaction> transactions = Collections.synchronizedList(new ArrayList<Transaction>());

  abstract String getName();

  abstract double interestEarned();

  public void deposit(double amount) {
    transactions.add(new Deposit(amount));
  }

  public void deposit(double amount, DateTime transactionTime) {
    transactions.add(new Deposit(amount, transactionTime));
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
    StringBuilder statement = new StringBuilder().append(this.getName()).append("\n");
    for(Transaction transaction : transactions)
      statement.append(transaction.getStatementLine());
    return statement.append("Total ").append(Util.toDollars(this.sumTransactions())).append("\n").toString();
  }

  public DateTime getLatestTransactionDate() {
    DateTime latestTransactionDate = null;
    for(Transaction transaction : transactions)
      if(latestTransactionDate == null || transaction.getTransactionDate().isAfter(latestTransactionDate))
        latestTransactionDate = transaction.getTransactionDate();
    return latestTransactionDate;
  }
}
