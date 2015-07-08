package com.abc.customer;

import com.abc.Utils;
import com.abc.account.Account;
import com.abc.account.AccountStatementGenerator;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private String name;
  private List<Account> accounts;

  public Customer(String name) {
    this.name = name;
    this.accounts = new ArrayList<Account>();
  }

  public String getName() {
    return name;
  }

  public void openAccount(Account account) {
    accounts.add(account);
  }

  public int getNumberOfAccounts() {
    return accounts.size();
  }

  public double totalInterestEarned() {
    double total = 0;
    for (Account a : accounts) {
      total += a.interestEarned();
    }
    return total;
  }

  public String getStatement() {
    String statement = "Statement for " + name + "\n";

    double total = 0.0;
    for (Account a : accounts) {
      AccountStatementGenerator generator = new AccountStatementGenerator(a);
      statement += "\n" + generator.getStatement() + "\n";

      total += a.getTotalTransactions();
    }
    statement += "\nTotal In All Accounts " + Utils.toDollars(total);

    return statement;
  }
}
