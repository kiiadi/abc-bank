package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


//Decision to throw IllegalArgumentException ( a runtime exception) rather than regular checked exception is debatable
//but I'm too lazy to change now, since this will effect the method signature and all the clients that call these methods.
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
    	for (Account acc : accounts) {
			
    		if(acc.getAccountType() == account.getAccountType()) {
    			
    			throw new IllegalArgumentException("account type " + account.getAccountType() + " already exists");
    		}
		}
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
    
    public void transfer(AccountType fromType, AccountType toType, double amount) {
    	
    	if(amount < 0.0) {
    		
    		throw new IllegalArgumentException("amount must be greater than zero");
    	}
    	
    	if(fromType == toType) {
    		
    		throw new IllegalArgumentException("from and to account can not be the same account type");
    	}
    	
    	Account from = null;
    	Account to = null;
    	for (Account account : accounts) {
			
    		if(account.getAccountType() == fromType) {
    			
    			from = account;
    		}else if(account.getAccountType() == toType) {
    			
    			to = account;
    		}
		}
    	
    	if(from == null) {
    		
    		throw new IllegalArgumentException("from account does not exist");
    	}
    	
    	if(to == null) {
    		
    		throw new IllegalArgumentException("to account does not exist");
    	}
    	
    	if(from.sumTransactions() < amount) {
    		
    		throw new IllegalArgumentException("from account does not have enough morney");
    	}
    	
    	from.withdraw(amount);
    	to.deposit(amount);
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
      
    	String s = a.getAccountType().name() + " Account\n";

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
            total += t.getAmount();
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
