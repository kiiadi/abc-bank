package com.abc.bank.customer;

import java.util.concurrent.ConcurrentHashMap;

import com.abc.bank.account.Account;
import com.abc.bank.account.AccountFactory;
import com.abc.bank.account.AccountType;
import com.abc.bank.exception.InsufficuentFundsException;
import com.abc.bank.exception.NoSuchAccountException;
import com.abc.bank.util.ConversionUtil;

/**
 * Represents a customer.
 *
 */
public class Customer {
    private final String id;
    private final String name;
    private final ConcurrentHashMap<AccountType, Account> accounts;

    public Customer(String id, String name) {
        this.id = id;
        this.name=name;
        this.accounts = new ConcurrentHashMap<AccountType,Account>();
    }

    public String getName() {
        return name;
    }

    public Account openAccount(AccountType type) {
        Account account = AccountFactory.newAccount(type);
        if(null!= accounts.putIfAbsent(type, account)){
            throw new IllegalArgumentException("The customer with id " + id + " already has a " +
                    type + " account");
        }
        return account;
    }

    public Account getAccount(AccountType type) throws NoSuchAccountException{
        Account account = accounts.get(type);
        if(null == account){
            throw new NoSuchAccountException("The customer with id " + id + " does not have a "
                    + type + " account");
        }
        else{
            return account;
        }
    }

    /**
     * Transfers the amount from one account to another
     * @param from
     * @param to
     * @param amount
     * @throws InsufficuentFundsException if the from account has insufficient funds
     * @throws NoSuchAccountException if either of the accounts does not exist for this customer
     */
    public void transfer(AccountType from, AccountType to, double amount) 
            throws InsufficuentFundsException, NoSuchAccountException{
        double amt = getAccount(from).withdraw(amount);
        getAccount(to).deposit(amt);
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts.values())
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts.values()) {
            statement += "\n" + a + "\n";
            total += a.getBalance();
        }
        statement += "\nTotal In All Accounts " + ConversionUtil.toDollars(total);
        return statement;
    }

    public String getSummary() {
        return getName() + " (" + format(getNumberOfAccounts(), "account") ;
    }
    
    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public String getId() {
        return id;
    }
}