package com.abc.api;

import java.util.Date;

import com.abc.Account;
import com.abc.Bank;
import com.abc.Customer;
import com.abc.exceptions.AccountOpenError;
import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.BankDetail;
import com.abc.interfaces.CustomerDetail;
import com.abc.interfaces.GeneralLedgerApi;
import com.abc.interfaces.JournalEntry;

public class GeneralLedger implements GeneralLedgerApi{

	private Bank m_bank;
	public GeneralLedger(String entityCode){
		m_bank = new Bank();
		m_bank.setCode(entityCode);
	}
	
	public JournalEntry createJournalEntry(String accountId, Date transactionDate, double d, int credit) {
 
		return null;
	}

	public BankDetail getInfo() {
		return m_bank;
	}
	
	private Customer createNewCustomer(String accountName){
		Customer customer = new Customer(accountName);
		m_bank.getCustomers().put(accountName, customer);
		return customer;
	}

	private Account createNewAccount(Customer customer, int accountType){
		Account ac = new Account(accountType);
		customer.getAccounts().put(accountType, ac);		
		return ac;
	}
	
	public AccountDetail openAccount(String accountName, int accountType) throws AccountOpenError {
		 		
		Customer customer = (Customer) getCustomerInfo(accountName);
		if (customer == null){			 			
			customer = createNewCustomer(accountName);			
		}
		AccountDetail acDetail =customer.getAccounts().get(accountType);
		if (acDetail != null){
			throw new AccountOpenError();
		}
		
		return createNewAccount(customer, accountType);
	}
	
	
	public CustomerDetail getCustomerInfo(String accountName) {			
		return  m_bank.getCustomers().get(accountName);
	}

}
