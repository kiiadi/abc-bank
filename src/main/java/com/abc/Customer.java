package com.abc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.abc.Utils.toDollars;
import static java.lang.Math.abs;

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
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for (Account a : accounts) {
            total = total.add(a.interestEarned());
        }
        return total;
    }

    public String getStatement() {
        String statement = "Statement for " + getName() + "\n";
        BigDecimal total = new BigDecimal(0);
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total = total.add(a.sumTransactions());
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder();
        s.append(a.getAccountType().getDescription()).append("\n");

        //Now total up all the transactions
        BigDecimal total = new BigDecimal(0);
        for (Transaction t : a.getTransactions()) {
            s.append("  ").append(t.getAmount().compareTo(BigDecimal.ZERO) == -1 ? "withdrawal" : "deposit").append(" ").append(toDollars(t.getAmount())).append("\n");
            total = total.add(t.getAmount());
        }
        s.append("Total ");
        s.append(toDollars(total));
        return s.toString();
    }

    public void transfer(Account from, Account to, BigDecimal amount) throws UnknownAccountException {
        Utils.ensurePositive(amount);
        ensureAccountIsKnown(from);
        ensureAccountIsKnown(to);
        from.withdraw(amount);
        to.deposit(amount);
    }

    private void ensureAccountIsKnown(Account from) throws UnknownAccountException {
        for (Account account :  accounts) {
            // account has no ID field, so just checking object equality here
            if (account == from) {
                return;
            }
        }
        throw new UnknownAccountException(this, from);
    }

}
