package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    public List<BaseAccount> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<BaseAccount>();
    }

    public String getName() {
        return name;
    }

    public List<BaseAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BaseAccount> accounts) {
		this.accounts = accounts;
	}

	public Customer openAccount(BaseAccount account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (BaseAccount a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (BaseAccount a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(BaseAccount a) {
        String s = "";

       //Translate to pretty account type
       if(a.getAccType().equalsIgnoreCase("0")){
                s += "Checking Account\n";
       }
       if(a.getAccType().equalsIgnoreCase("1")){
           s += "Savings Account\n";
       }
       
       if(a.getAccType().equalsIgnoreCase("2")){
           s += "Maxi Account\n";
       }


        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
