package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {

    private final String name;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Account> accounts;

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

    /**
     *
     * @param from
     * @param to
     * @param amount
     * @return success
     * @throws java.lang.Exception
     */
    public boolean transferBetweenAccounts(int from, int to, double amount) throws Exception {
        boolean success = false;
        Account fromAcct = findAccountById(from);
        Account toAcct = findAccountById(to);
        if (null == fromAcct || null == toAcct) {
            throw new Exception("Account not found");
        }
        boolean enough = isSufficientFunds(fromAcct, amount);
        if (!enough) {
            throw new Exception("insufficient funds");
        }

        fromAcct.withdraw(amount);
        toAcct.deposit(amount);
        double transfered = fromAcct.getLastTransaction().amount + toAcct.getLastTransaction().amount;
        double expected = 0.0;
        if (transfered == expected) {
            success = true;
        }
        return success;
    }

    /**
     *
     * @param fromAcct
     * @param amount
     * @return
     */
    private boolean isSufficientFunds(Account fromAcct, double amount) {
        boolean enough = false;
        double currentBallance = fromAcct.sumTransactions();
        if (amount <= currentBallance) {
            enough = true;
        }
        return enough;

    }

    private Account findAccountById(int id) {
        for (Account a : accounts) {
            if (a.getAccountType() == id) {
                return a;
            }
        }
        return null;
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
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

        //Translate to pretty account type
        switch (a.getAccountType()) {
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }
}
