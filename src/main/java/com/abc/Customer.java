package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

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

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account account : accounts)
            total += account.interestEarned();
        return total;
    }

    public String getStatement() {
	/* Use efficient string concatenation */
	StringBuilder statement = new StringBuilder("Statement for " + name + "\n"); 
        double total = 0.0;
        for (Account account : accounts) {
            statement.append("\n" + statementForAccount(account) + "\n");
            total += account.sumTransactions();
        }
        statement.append("\nTotal In All Accounts " + toDollars(total));
        return statement.toString();
    }

    private String statementForAccount(Account account) {
	/* Use efficient string concatenation */
	StringBuilder statement = new StringBuilder("");

       //Translate to pretty account type
        switch(account.getAccountType()){
            case CHECKING:
                statement.append("Checking Account\n");
                break;
            case SAVINGS:
                statement.append("Savings Account\n");
                break;
            case MAXI_SAVINGS:
                statement.append("Maxi Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction transaction : account.getTransactions()) {
            statement.append("  " + (transaction.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(transaction.getAmount()) + "\n");
            total += transaction.getAmount();
        }
        statement.append("Total " + toDollars(total));
        return statement.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
