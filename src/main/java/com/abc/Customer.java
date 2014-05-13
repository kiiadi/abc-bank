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

  /***
   * @throws IllegalArgumentException if amount is less than or equal to zero or toAccount or fromAccount not found
   */
  public void transfer(double amount, Account fromAccount, Account toAccount) {
    if(!accounts.contains(fromAccount)) {
      throw new IllegalArgumentException("Source account not found under customer.");
    } else if(!accounts.contains(toAccount))  {
      throw new IllegalArgumentException("Destination account not found under customer.");
    } else if(amount <= 0.0) {
      throw new IllegalArgumentException("Transfer amount must be greater than zero.");
    }
    fromAccount.withdraw(amount);
    toAccount.deposit(amount);
  }
}
