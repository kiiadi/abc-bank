package com.abc.interfaces;

import java.util.Date;

import com.abc.exceptions.*;
 
public interface GeneralLedgerApi {
	BankDetail getBankDetail();	
	AccountDetail openAccount(String accountName, int accountType)throws AccountOpenError;
	CustomerDetail getCustomer(String accountName);
	JournalEntry createJournalEntry(String accountName, int accountType, Date transactionDate, double amount, int credit)  throws InvalidAccount, CustomerNotFound, TransactionAmountIsLessThanZero;	
	JournalEntry deposit(String accountName, int accountType, Date transactionDate, double amount) throws InvalidAccount,CustomerNotFound,TransactionAmountIsLessThanZero;
	JournalEntry withdraw(String accountName, int accountType, Date transactionDate, double amount)  throws InvalidAccount,CustomerNotFound,TransactionAmountIsLessThanZero;
	double calculateInterestEarned(String accountName, int accountType) throws CustomerNotFound, InvalidAccount;
	
}