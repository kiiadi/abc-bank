package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	public static final int CHECKING = 0; 
	public static final int SAVINGS = 1; 
    public static final int MAXI_SAVINGS = 2;
    
    private final int accountType; 
    public List<Transaction> transactions; 
    
    public Account(int accountType) 
    { 
    	this.accountType = accountType; 
 	    this.transactions = new ArrayList<Transaction>(); 
  	}
    
    public void deposit(double amount)
    {
        if (amount <= 0) { 
            throw new IllegalArgumentException("amount must be greater than zero"); 
        } else { 
            transactions.add(new Transaction(amount)); 
        } 
    }
    
    public void withdraw(double amount) 
    {
	    if (amount <= 0) { 
	        throw new IllegalArgumentException("amount must be greater than zero"); 
	    } else { 
	        transactions.add(new Transaction(-amount)); 
	    } 
	}
    
    //The last additional feature mentioned in the description is ambiguous.
    
    //I just have provided a method that can give you the daily interest value that 
    //can be used for any different implementations. 
    
    //1. It can be used as a simple deposit transaction into the account. 
    //(But thats not the case in Bank's regular accounts)
    
    //2. It can be used by Bank to create a daily BatchJob to calculate and have a separate
    //object designed to store the account, date and interest value.
    
    //3. Bank can have a method to call all the account's daily interest value as a statement.
    
    public double getDailyInterestAccrual()
    {
    	double dblInterest = interestEarned(); 
    	if(dblInterest>0)
    		return dblInterest/365;
    	else
    	 return 0;
    }
    
    public double interestEarned() 
    {
	    double amount = sumTransactions();
	    if(amount>0)
	    {
		    switch(accountType){ 
		            case SAVINGS: 
		                if (amount <= 1000) 
		                    return amount * 0.001; 
		                else 
		                    return 1 + (amount-1000) * 0.002;
		//    	            case SUPER_SAVINGS: 
		//    	                if (amount <= 4000) 
		//    	                    return 20; 
		            case MAXI_SAVINGS: 
		                
		            	/*if (amount <= 1000) 
		                    return amount * 0.02; 
		                if (amount <= 2000) 
		                    return 20 + (amount-1000) * 0.05; 
		                return 70 + (amount-2000) * 0.1;*/
		            	
		            	if (isOfferEligible())
		            		return amount * 0.05;
		            	 else 
		            		return amount * 0.001;
		            
		            default: 
		    }          return amount * 0.001;
    	} else { 
    		return 0;
	    }
    }
    
    public double sumTransactions() 
    {
           return checkIfTransactionsExist(true); 
    }
    
    private double checkIfTransactionsExist(boolean checkAll) 
    { 
        double amount = 0.0; 
        for (Transaction t: transactions) 
            amount += t.amount; 
        return amount; 
    } 
    
    private boolean isOfferEligible()
    {
    	if (!transactions.isEmpty() && transactions.size()>0)
    	{
	    	for (Transaction t: transactions)
	    	{
	    		if (t.transactionDate.after(DateProvider.getInstance().rebateEligibleDate()) && t.amount<0) 
	    			return false;
	    	}
	    	return true;
    	} else 
    		return false;
    }
    
    public int getAccountType() 
    {
    	return accountType; 
    }
}
