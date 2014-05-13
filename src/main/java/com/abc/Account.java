package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {

  private List<Transaction> transactions = Collections.synchronizedList(new ArrayList<Transaction>());

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
    StringBuilder statement = new StringBuilder().append(this.getName()).append("\n");
    for(Transaction transaction : transactions)
      statement.append(transaction.getStatementLine());
    return statement.append("Total ").append(Util.toDollars(this.sumTransactions())).toString();
  }
}
