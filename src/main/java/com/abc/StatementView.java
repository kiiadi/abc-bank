package com.abc;


import static com.abc.MonetaryFormatter.*;

public class StatementView {

    public String render(Customer customer) {
        String statement = renderStatementHeader(customer);
        for (Account account : customer.getAccounts())
            statement += renderAccountStatement(account);

        return statement += renderAccountTotals(customer);
    }

    private String renderType(Account account) {
        return account.getAccountType().toString() + " Account\n";
    }

    private String renderTransaction(Transaction transaction) {
        return "  " + transaction.getTransactionType() + " " + toDollars(transaction.getAmount()) + "\n";
    }

    private String renderTotal(Account account) {
        return "Total " + toDollars(account.currentBalance());
    }

    private String renderAccountStatement(Account account) {
        String statement = renderType(account);
        for (Transaction t : account.getTransactions())
            statement += renderTransaction(t);
        statement += renderTotal(account);
        return "\n" + statement + "\n";
    }

    private String renderAccountTotals(Customer customer) {
        return "\nTotal In All Accounts " + toDollars(customer.totalBalance());
    }

    private String renderStatementHeader(Customer customer) {
        return "Statement for " + customer.getName() + "\n";
    }
}
