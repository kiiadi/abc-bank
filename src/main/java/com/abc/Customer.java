package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Class to create a customer for a bank. Can be created using Bank
 */
public class Customer {
    private String name;
    private long id; // Not Used: just an idea on how global Id can be generated
    private HashMap<Integer, Account> accounts;

    public Customer(String name, int accountType) {
        this.name = name;
        this.id = CustomerId.getInstance().getNextId(); //Global Id for customer
        this.accounts = new HashMap<Integer, Account>();
        openAccount(accountType);
    }

    /**
     * Gets the name of customer
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Open an Account for the customer based on the Account Classification
     * @param accountType
     * @return
     */
    public Account openAccount(int accountType) {
        Account account = new Account(accountType);
        accounts.put(accountType,account);
        return account;
    }

    /**
     * Returns an account object based on its Type.
     * @param accountType
     * @return
     */
    public Account getAccount(int accountType){
        if(accounts.containsKey(accountType)){
            return accounts.get(accountType);
        }else {
            throw new IllegalArgumentException("Account Type Not Found");
        }
    }

    /**
     * provides total number of accounts for the customer
     * @return
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Calculates total interest earned by the account
     * @return
     */
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts.values())
            total += a.interestEarned();
        return total;
    }

    /**
     * Provides a detailed report including transaction and consolidated balance for
     * individual accounts
     * @return
     */
    public String getStatement() {
        StringBuffer statement = new StringBuffer("Statement for " + name + "\n");

        double total = 0.0;
        for (Account a : accounts.values()) {
            statement.append("\n" + statementForAccount(a) + "\n");
            total += a.getAccountBalance();
        }
        statement.append("\nTotal In All Accounts " + toDollars(total));
        return statement.toString();
    }

    /**
     * Transfers money from a source Account to Destination Account
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(Account from, Account to, double amount){
        from.withdraw(amount);
        to.deposit(amount);
    }

    /**
     * Provides a detailed report including transaction and consolidated balance for
     * the customer
     * @param a
     * @return
     */
    private String statementForAccount(Account a) {
        StringBuffer s = new StringBuffer();

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                s.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                s.append("Maxi Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s.append("  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") +
                    " " + toDollars(t.getAmount()) + "\n");
            total += t.getAmount();
        }
        s.append("Total " + toDollars(total));
        return s.toString();
    }

    /**
     * Converts double to user readable format
     * @param d
     * @return
     */
    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
