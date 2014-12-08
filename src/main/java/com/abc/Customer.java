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
        for (Account a : accounts) 
            total += a.interestEarned(); 
        return total; 
    }

    public double totalDailyInterestAccrued()
    {
        double totalDailyInterest = 0; 
        for (Account a : accounts) 
        	totalDailyInterest += a.getDailyInterestAccrual(); 
        return totalDailyInterest;
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

    private boolean validateAccount(Account account)
    {
    	if ((!accounts.isEmpty() && accounts.size()>0) && account!=null)
    	{
	    	for (Account a : accounts) {
	    		if (a.getAccountType()==(account.getAccountType()))
	    		{
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
    
    public boolean transferFunds(Account From, Account To, double transferAmount)
    {
    	if(validateAccount(From) && validateAccount(To) && transferAmount>0)
    	{
	    	if(From.getAccountType()==To.getAccountType())
	    	{
	    		return false;
	    	} else {
	    		From.withdraw(transferAmount);
	    		To.deposit(transferAmount);
	    		return true;
	    	}
    	}
    		
    	return false;
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
} 
