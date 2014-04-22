/**
 * 
 */
package com.abc.customer;

import java.util.Map;
import java.util.TreeMap;

import com.abc.accounts.Account;
import com.abc.accounts.AccountFactory;
import com.abc.accounts.AccountType;
import com.abc.exceptions.DuplicateAccountException;
import com.abc.exceptions.InsufficientFundsException;
import com.abc.exceptions.InvalidAccountIdException;
import com.abc.exceptions.InvalidTransactionAmountException;

/**
 * A bank client.
 *
 */
public class Customer {

	private int customerId;
	private String name;
    private Map<Integer, Account> accountMap;

	public Customer(int id, String name) {
        this.customerId = id;
    	this.name = name;
        this.accountMap = new TreeMap<Integer, Account>(); // use sorted map for ordered keys
    }

    public int getCustomerId() {
        return customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public Map<Integer, Account> getAccounts() {
		return accountMap;
	}
    
    public Customer openAccount(AccountType accountType) throws DuplicateAccountException {
    	Account account = AccountFactory.createAccount(accountType);
    	
    	addAccount(account);
        return this;
    }
    
    public Customer addAccount(Account account) throws DuplicateAccountException {
    	if(account == null) {
    		return this;
    	}
    	
    	if(accountMap.containsKey(account.getAccountId())) {
			throw new DuplicateAccountException("Duplicate account");
		}
		accountMap.put(account.getAccountId(), account);
		
    	return this;
    }
    
    /**
     * Used to transfer fund between accounts.
     * 
     * @param sourceAccount
     * @param targetAccount
     * @param amount
     * @return
     * @throws InvalidAccountIdException, InsufficientFundsException, InvalidTransactionAmountException
     */
    public void transferFundByAccount(Account sourceAccount, Account targetAccount,  double amount)
    		throws InvalidAccountIdException, InsufficientFundsException, InvalidTransactionAmountException {
    	
    	transferFundByAccountId(sourceAccount.getAccountId(), targetAccount.getAccountId(),  amount);
    }
    
    private void transferFundByAccountId(int sourceAccountId, int targetAccountId,  double amount)
    		throws InvalidAccountIdException, InsufficientFundsException, InvalidTransactionAmountException {
    	
    	Account sourceAccount = accountMap.get(sourceAccountId);
    	if(sourceAccount == null) {
    		throw new InvalidAccountIdException("Invalid source account id - " + sourceAccountId);
    	}
    	
    	Account targetAccount = accountMap.get(targetAccountId);
    	if(targetAccount == null) {
    		throw new InvalidAccountIdException("Invalid target account id - " + targetAccountId);
    	}
    	    	
    	sourceAccount.withdraw(amount);
    	targetAccount.deposit(amount);
    }

    public int getNumberOfAccounts() {
        return accountMap.size();
    }
    
    public double totalInterestEarned() {
        double total = 0;
        
        for (Account account : accountMap.values()) {
            total += account.interestEarned();
        }
        
        return total;
    }

    public String getStatement() {
        return CustomerReport.getStatement(this);
    }
}
