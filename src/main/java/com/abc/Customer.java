package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
  private String name;
  private List<Account> accounts = Collections.synchronizedList(new ArrayList<Account>());

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
    StringBuilder statement = new StringBuilder().append("Statement for ").append(name).append("\n");
    double total = 0.0;
    for(Account account : accounts) {
      statement.append("\n").append(account.getStatementText()).append("\n");
      total += account.sumTransactions();
    }
    return statement.append("\nTotal In All Accounts ").append(Util.toDollars(total)).toString();
  }
}
