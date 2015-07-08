package com.abc.account;

import com.abc.Transaction;
import com.abc.Utils;

import java.util.List;

public class AccountStatementGenerator {
  private String statement;

  public AccountStatementGenerator(Account account) {
    addDescription(account.getAccountType());
    addTransactions(account.getTransactions());
  }

  private void addTransactions(List<Transaction> transactions) {
    double total = 0.0;
    for (Transaction transaction : transactions) {
      statement += "  " + transaction.getType().getLabel() + " " + Utils.toDollars(transaction.getAmount()) + "\n";
      total += transaction.getAmount();
    }
    statement += "Total " + Utils.toDollars(total);
  }

  private void addDescription(AccountType accountType) {
    AccountTypeDescriptor descriptor = new AccountTypeDescriptor();
    AccountType.Utils.visit(accountType, descriptor);
    statement = descriptor.getDescription() + "\n";
  }

  public String getStatement() {
    return statement;
  }
}
