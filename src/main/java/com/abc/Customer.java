package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private String name;
  private List<Account> accounts = new ArrayList<Account>();

  public Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Customer openAccount(Account account) {
    accounts.add(account);
    return this;
  }

  public int getNumberOfAccounts() {
    return accounts.size();
  }

  public double totalInterestEarned() {
    double total = 0;
    for(Account account : accounts)
      total += account.interestEarned();
    return total;
  }

  public String getStatement() {
    String statement;
    statement = "Statement for " + name + "\n";
    double total = 0.0;
    for(Account account : accounts) {
      statement += "\n" + account.getStatementText() + "\n";
      total += account.sumTransactions();
    }
    statement += "\nTotal In All Accounts " + Util.toDollars(total);
    return statement;
  }
}
