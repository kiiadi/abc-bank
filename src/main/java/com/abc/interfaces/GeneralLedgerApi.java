package com.abc.interfaces;

import java.util.Date;

import com.abc.exceptions.AccountOpenError;

public interface GeneralLedgerApi {

	BankDetail getInfo();
	JournalEntry createJournalEntry(String accountId, Date transactionDate, double d, int credit);
	AccountDetail openAccount(String accountName, int accountType)throws AccountOpenError;
	CustomerDetail getCustomerInfo(String accountName);
	
}