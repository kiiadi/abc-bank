package com.abc;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * The Class Customer.
 */
public class Customer {
   	
    /** The customerName. */
    private String customerName = null;
    
    /** The customerId */
    private int customerId = 0;
    
    /** The accounts. */
    private Map<Integer, Account> accounts = null;  
    

    
    /**
     * Instantiates a new customer.
     * For accounts we will use 
     *
     * @param name the name of the customer
     * @param cId the customer id 
     */
    public Customer(String name, int cId) {
        this.customerName = name;
        this.customerId = cId;
               
        this.accounts = (Map<Integer, Account>)Collections.synchronizedMap(new HashMap<Integer, Account>());   
    }               

    
    /**
     * Gets the customerName.
     *
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    
    
    /**
     * Gets the customerId.
     *
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }
    

    /**
     * Open account.  
     *
     * @param account the account
     */
    public void openAccount(Account account) {
        accounts.put(account.getAccountType(), account);
    }
    
    
    /**
     * Get an account by its accountType.  
     *
     * @param accountType the accountType
     * @return the Account for the given accountType
     */
    private Account getAccount(int accountType) {
        return accounts.get(accountType);
    }
 
    
    /**
     * Gets the number of accounts.
     *
     * @return the number of accounts
     */
    public synchronized int getNumberOfAccounts() {
        return accounts.size();
    }
   

    /**
     * Total compound interest earned.    
     *
     * @return the total compound interest earned for a customer, as a double
     */
    public synchronized double totalCompoundInterestEarned() {
        double total = 0;
        
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
        	Account a = entry.getValue();
            total += a.compoundInterestEarned();
        }
        
        return total;
    }
    

    /**
     * Total compound interest earned.    
     *
     * @return the total compound interest earned for a customer, as a double
     */
    public synchronized double getBalance() {
        double balance = 0;
        
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
        	Account a = entry.getValue();
            balance += a.getBalance();
        }
        
        return balance;
    }
    
    
    /**
     * Check if transactions exist for this customer.
     *
     * @return true if any transactions exist, in any account
     */
    public synchronized boolean checkIfTransactionsExist() {
    	
    	//DIVER - can I use an AccountImpl here against accounts ??
    	for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            Account a = entry.getValue();
            
            if (a.checkIfTransactionsExist()) {
            	return true;
            }
        }
        return false;
    }
    
    
    /**
     * Transfer funds from Account1 to Account2<br>       
     * This is essentially a withdrawal from Account1, followed by a deposit to Account2 
     *
     * @param transferAccountTypeFrom the accountType to transfer "from" 
     * @param transferAccountTypeTo the accountType to transfer "to"
     * @param transferAmount the amount to transfer
     * @return 0 on success; otherwise a negative number to denote the error
     */       
    public synchronized int transfer(int transferAccountTypeFrom, int transferAccountTypeTo, double transferAmount) {
    	
    	Account transferAccountFrom = getAccount(transferAccountTypeFrom);
    	
    	int withdrawalRetCode = transferAccountFrom.withdraw(transferAmount);
    	
    	if (withdrawalRetCode == 0) {
    		Account transferAccountTo = getAccount(transferAccountTypeTo);
    		
    		int depositRetCode = transferAccountTo.deposit(transferAmount);
    		
    		if (depositRetCode == 0) {
    			return 0;
    		} else {
    			//TODO: When we connect to a dataSource we will want to set up a transaction for this and roll it back...
    			return depositRetCode;
    		}    		
    	} else {
    		return withdrawalRetCode;
    	}
    	
    }
    
    
    /**
     * Gets the bank statement for this Customer.     
     *
     * @showInterestEarned a flag used to also show the interest earned
     * @return the statement for this Customer's accounts as a String
     */
    public synchronized String getStatement(boolean showInterestEarned) {
        
        StringBuffer statementBuff = new StringBuffer();
        
        statementBuff.append("Statement for ");
        statementBuff.append(getCustomerName());
        statementBuff.append(" : ");
        statementBuff.append(getCustomerId());
        statementBuff.append("\n");
                
        double totalBalance = 0.0;
        double totalInterest = 0.0;
        
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            Account a = entry.getValue();
        
        	statementBuff.append("\n");
        	a.statementForAccount(statementBuff, showInterestEarned);
        	statementBuff.append("\n");
            
            totalBalance += a.getBalance();
            totalInterest += a.compoundInterestEarned();
        }
        statementBuff.append("\nTotal In All Accounts ");
        statementBuff.append(Account.toDollars(totalBalance));

        statementBuff.append("\nInterest Earned In All Accounts ");
        statementBuff.append(Account.toDollars(totalInterest));
        
        return statementBuff.toString();
    }

    
}
