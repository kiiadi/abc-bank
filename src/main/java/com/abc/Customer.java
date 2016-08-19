//package com.abc;

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
    	String type = "";
    	if(account.getAccountType() == Account.CHECKING){
    		type = "checking";
    	}else if(account.getAccountType() == Account.SAVINGS){
    		type = "savings";
    	}else
    		type = "maxisavings";

    	//System.out.println("Open account " + type);
        accounts.add(account);
        return this;
    }
    
    public String transfer(Account from, Account to, double amt){
    	from.withdraw(amt);
    	to.deposit(amt);
    	return getStatement();
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
        //statement += "Number of accts " + getNumberOfAccounts() + "\n";

        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        System.out.println(statement);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";
        //s = "Statement for " + name + "\n";
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
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount);
            total += t.amount;
            s += " date: " + t.getDate()  + "\n";
        }
        s += "Total " + toDollars(total);
        System.out.println(s);

        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
}
