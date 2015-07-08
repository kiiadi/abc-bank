package com.abc.account;

import com.abc.Transaction;
import com.abc.Utils;

import java.util.List;

public class AccountStatementGenerator {
  private final StringBuilder statement = new StringBuilder();

  public AccountStatementGenerator(Account account) {
    addDescription(account.getAccountType());
    addTransactions(account.getTransactions());
  }

  private void addTransactions(List<Transaction> transactions) {
    double total = 0.0;
    for (Transaction transaction : transactions) {
      statement
        .append("  ")
        .append(transaction.getType().getLabel())
        .append(" ")
        .append(Utils.toDollars(transaction.getAmount()))
        .append("\n");

      total += transaction.getAmount();
    }
    statement
      .append("Total ")
      .append(Utils.toDollars(total));
  }

  private void addDescription(AccountType accountType) {
    statement
      .append(accountType.getLabel())
      .append("\n");
  }

  public String getStatement() {
    return statement.toString();
  }
}
