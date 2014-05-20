package com.abc.api;

import java.util.Date;

import com.abc.Bank;
import com.abc.exceptions.AccountOpenError;
import com.abc.exceptions.CustomerNotFound;
import com.abc.exceptions.InvalidAccount;
import com.abc.exceptions.TransactionAmountIsLessThanZero;
import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.BankDetail;
import com.abc.interfaces.CustomerDetail;
import com.abc.interfaces.GeneralLedgerApi;
import com.abc.interfaces.JournalEntry;
import com.abc.interfaces.TransactionType;

public class GeneralLedger implements GeneralLedgerApi{

	private Bank m_bank;
	public GeneralLedger(String entityCode){
		m_bank = new Bank();
		m_bank.setCode(entityCode);
	}
		 	
	public JournalEntry createJournalEntry(String accountName, int accountType, Date transactionDate, double amount, int transactionType) throws InvalidAccount,CustomerNotFound,TransactionAmountIsLessThanZero {
		
		if (amount <=0){
			throw new TransactionAmountIsLessThanZero();
		}
		
		CustomerDetail customer = getCustomer(accountName);
		if (customer == null){
	 		throw new CustomerNotFound();
	 	}
		
	 	AccountDetail account = customer.getAccounts().get(accountType);
	 	if (account == null){
	 		throw new InvalidAccount();
	 	}	 	
		return account.createNewTransaction(transactionDate, amount, transactionType);
	}

	public JournalEntry deposit(String accountName, int accountType, Date transactionDate, double amount)  throws InvalidAccount,CustomerNotFound,TransactionAmountIsLessThanZero{		
		return createJournalEntry(accountName, accountType, transactionDate, amount, TransactionType.CREDIT);
	}
	
	public JournalEntry withdraw(String accountName, int accountType, Date transactionDate, double amount)  throws InvalidAccount,CustomerNotFound,TransactionAmountIsLessThanZero{		
		return createJournalEntry(accountName, accountType, transactionDate, amount, TransactionType.DEBIT);
	}
	
	
	public BankDetail getBankDetail() {
		return m_bank;
	}
		
	public AccountDetail openAccount(String accountName, int accountType) throws AccountOpenError {
		 		
		CustomerDetail customer = getCustomer(accountName);
		if (customer == null){			 			
			customer = m_bank.createNewCustomer(accountName);			
		}
		AccountDetail acDetail =customer.getAccounts().get(accountType);
		if (acDetail != null){
			throw new AccountOpenError();
		}		
		return customer.createAccount(accountType);
	}
	
	
	public CustomerDetail getCustomer(String accountName) {			
		return  m_bank.getCustomers().get(accountName);
	}

	

 
}
