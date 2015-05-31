package com.abc;

import static java.lang.Math.abs;

public class Statement {

    private final Customer customer;

    public Statement(Customer customer) {
        this.customer = customer;
    }

    public String generateStatement() {
        StringBuilder statement = new StringBuilder("Statement for ").append(customer.getName()).append('\n');
        double total = 0.0;
        for (Account account : customer.getAccounts()) {
            statement.append('\n')
                    .append(statementForAccount(account))
                    .append('\n');
            total += account.getBalance();
        }
        statement.append("\nTotal In All Accounts ").append(formatToDollars(total));
        return statement.toString();
    }

    private static String statementForAccount(Account account) {
        StringBuilder statement = new StringBuilder(account.getName()).append(" Account\n");

        //Now display all the transactions
        for (Transaction transaction : account.getTransactions()) {
            statement.append("  ")
                     .append(transaction.getName())
                     .append(' ')
                     .append(formatToDollars(transaction.getAmount()))
                     .append('\n');
        }
        statement.append("Total ").append(formatToDollars(account.getBalance()));
        return statement.toString();
    }

    private static String formatToDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }
}
