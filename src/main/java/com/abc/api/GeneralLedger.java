package com.abc.api;

import java.util.Date;

import com.abc.exceptions.GLError;
import com.abc.exceptions.GLErrorCodes;
import com.abc.interfaces.AccountDetail;
import com.abc.interfaces.AccountType;
import com.abc.interfaces.BankDetail;
import com.abc.interfaces.CustomerDetail;
import com.abc.interfaces.GeneralLedgerApi;
import com.abc.interfaces.JournalEntry;
import com.abc.interfaces.TransactionType;
import com.abc.persistance.Bank;

public class GeneralLedger implements GeneralLedgerApi {

	private Bank m_bank;

	public GeneralLedger(String entityCode) {
		m_bank = new Bank();
		m_bank.setCode(entityCode);
	}

	public JournalEntry createJournalEntry(String accountName, int accountType, Date transactionDate, double amount,
			int transactionType) throws GLError {

		if (amount <= 0) {
			throw new GLError(GLErrorCodes.GL_TRANSACTION_AMOUNT_ZERO_OR_LESS);
		}

		AccountDetail account = getAccountDetail(accountName, accountType);
		return account.createNewTransaction(transactionDate, amount, transactionType);
	}

	private AccountDetail getAccountDetail(String accountName, int accountType) throws GLError {
		CustomerDetail customer = getCustomer(accountName);
		if (customer == null) {
			throw new GLError(GLErrorCodes.GL_CUSTOMER_NOT_FOUND);
		}

		AccountDetail account = customer.getAccounts().get(accountType);
		if (account == null) {
			throw new GLError(GLErrorCodes.GL_INVALID_ACCOUNT);
		}
		return account;
	}

	public JournalEntry deposit(String accountName, int accountType, Date transactionDate, double amount) throws GLError {
		return createJournalEntry(accountName, accountType, transactionDate, amount, TransactionType.CREDIT);
	}

	public JournalEntry withdraw(String accountName, int accountType, Date transactionDate, double amount) throws GLError {
		return createJournalEntry(accountName, accountType, transactionDate, amount, TransactionType.DEBIT);
	}

	public BankDetail getBankDetail() {
		return m_bank;
	}

	public AccountDetail openAccount(String accountName, int accountType) throws GLError {

		CustomerDetail customer = getCustomer(accountName);
		if (customer == null) {
			customer = m_bank.createNewCustomer(accountName);
		}
		AccountDetail acDetail = customer.getAccounts().get(accountType);
		if (acDetail != null) {
			throw new GLError(GLErrorCodes.GL_ACCOUNT_OPEN_ERROR);
		}
		return customer.createAccount(accountType);
	}

	public CustomerDetail getCustomer(String accountName) {
		return m_bank.getCustomers().get(accountName);
	}
	

	public double calculateInterestEarned(String accountName, int accountType) throws GLError {
		AccountDetail account = getAccountDetail(accountName, accountType);
		double amount = account.getBalance();
		switch (account.getAccountType()) {
		case AccountType.SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;

		case AccountType.MAXI_SAVINGS:
			if (amount <= 1000)
				return amount * 0.02;
			if (amount <= 2000)
				return 20 + (amount - 1000) * 0.05;
			return 70 + (amount - 2000) * 0.1;
		default:
			return amount * 0.001;
		}
	}

 
}
