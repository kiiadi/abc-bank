package com.abc.service;

import static com.abc.utils.Utils.EOL;
import static com.abc.utils.Utils.toDollars;

import java.util.Map;

import com.abc.domain.Account;
import com.abc.domain.AccountType;
import com.abc.domain.Customer;

public class CustomerService {
	
	private AccountService accountService;
	
	public void addAccount(Customer customer, Account account) {
		
		Map<AccountType, Account> accounts = customer.getAccounts();
        AccountType requestedAccountType = account.getAccountType();
        if(accounts.get(requestedAccountType) != null) {
        	throw new RuntimeException("Customer to already has " + requestedAccountType.getType() + " account.");
        } else {
        	accounts.put(requestedAccountType, account);
        }
    }
	
    public int getNumberOfAccounts(Customer customer) {
    	if(customer == null) {
    		throw new IllegalArgumentException("Attempt to get accounts for null customer.");
    	}
    	return customer.getAccounts().size();	
    }
    
    public double getTotalInterestEarned(Customer customer) {
        double total = 0;
        boolean useSpecialIntrestRate = false;
        for (Account account : customer.getAccounts().values()) {
        	useSpecialIntrestRate = accountService.isSpecialInterestRateApplicable(account);
            total += accountService.getInterestEarned(account, useSpecialIntrestRate);
        }
        return total;
    }
    
    public String getStatement(Customer customer) {
    	if(customer == null) { 
    		throw new IllegalArgumentException("Attempt to get statement for null customer.");
    	}
    	
    	StringBuilder statement = new StringBuilder();
        statement.append("Statement for ").append(customer.getName()).append(EOL);
        double total = 0.0;
        for (Account account : customer.getAccounts().values()) {
            statement.append(EOL).append(accountService.getStatement(account)).append(EOL);
            total += accountService.sumTransactions(account);
        }
        statement.append(EOL).append("Total In All Accounts ").append(toDollars(total));
        return statement.toString();
    }
    
    public void transfer(Customer customer, AccountType sourceAccountType, AccountType destinationAccountType, double amount) {
    	if(customer == null) { 
    		throw new IllegalArgumentException("Attempt to perform transfer operation for null customer.");
    	}

    	Account sourceAccount = customer.getAccounts().get(sourceAccountType);
    	if(sourceAccount == null) {
    		throw new IllegalArgumentException("No account of type ." + sourceAccountType.getCode());
    	}
    	Account destinationAccount = customer.getAccounts().get(destinationAccountType);
    	if(destinationAccount == null) {
    		throw new IllegalArgumentException("No account of type ." + destinationAccountType.getCode());
    	}
    	if(sourceAccountType.equals(destinationAccountType)) {
    		return;
    	}
    	if(accountService.sumTransactions(sourceAccount) >= amount) {
    		accountService.withdraw(sourceAccount, amount);
    		accountService.deposit(destinationAccount, amount);
    	}
    }

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
}
