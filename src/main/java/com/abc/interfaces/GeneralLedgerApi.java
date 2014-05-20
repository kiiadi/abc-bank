package com.abc.interfaces;

import java.util.Date;

import com.abc.exceptions.*;
 
public interface GeneralLedgerApi {
	BankDetail getBankDetail();	
	AccountDetail openAccount(String accountName, int accountType)throws GLError;
	CustomerDetail getCustomer(String accountName);
	JournalEntry createJournalEntry(String accountName, int accountType, Date transactionDate, double amount, int credit)  throws GLError;	
	JournalEntry deposit(String accountName, int accountType, Date transactionDate, double amount) throws GLError ;
	JournalEntry withdraw(String accountName, int accountType, Date transactionDate, double amount)  throws GLError ;
	double calculateInterestEarned(String accountName, int accountType) throws GLError;
	
}