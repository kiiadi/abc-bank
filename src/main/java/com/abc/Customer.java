package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
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

    public BigDecimal totalInterestEarned() {
        BigDecimal total = new BigDecimal(0.0);
        for (Account a : accounts)
            total = total.add(a.interestEarned());
        return total;
    }

    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {

        if(!accounts.contains(fromAccount) || !accounts.contains(toAccount)) {
            throw new IllegalArgumentException("Account does not belong to the Customer");
        }

        if(amount.compareTo(fromAccount.sumTransactions()) > 0) {
            throw new IllegalArgumentException("Not enough funds to transfer"+ amount + " from "+ fromAccount.sumTransactions());
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

    }

    public String getStatement() {
        String statement  = "Statement for " + name + "\n";
        BigDecimal total = new BigDecimal(0.0);
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total = total.add(a.sumTransactions());
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {

       //Translate to pretty account type
        StringBuilder s = new StringBuilder();
        s.append(a.getAccountName()).append("\n");


        //Now total up all the transactions
        BigDecimal total = new BigDecimal(0.0);
        for (Transaction t : a.transactions) {
            s.append("  ").append( (t.amount.signum() < 0 ? "withdrawal" : "deposit") ).append(" ").append(toDollars(t.amount)).append("\n");
            total = total.add(t.amount);
        }
        s.append("Total ").append(toDollars(total));
        return s.toString();
    }

    private String toDollars(BigDecimal d){
        return String.format("$%,.2f", d.abs());
    }
}
