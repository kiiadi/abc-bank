package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    //It's important to have a customer id so if two customers named "John Smith" come along you can differentiate between them.
    //Ideally you would use a hash function to create a unique id or maybe a list of all customers and set the id equal to their
    //position in the list.
    private int customer_id;
    
    private List<Account> accounts;

    public Customer(String name, int id) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
        this.customer_id = id;
    }

    public String getName() {
        return name;
    }
  
    public int getCustomer_id(){
    	return customer_id;
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
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
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
        switch(a.getAccountType()){
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

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    //To transfer between accounts just withdraw from account 1 and deposit that amount in account 2.
    public void transferBtwnAccounts(Account transferFrom, Account transferTo, double amount){
    	if(amount <= 0){
    		throw new IllegalArgumentException("amount must be greater than zero");
    	}
    	else{
    		transferFrom.withdraw(amount);
    		transferTo.deposit(amount);
    	}
    }
}
