package com.abc.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.exception.InvalidTransactionException;
import com.abc.transaction.Deposit;
import com.abc.transaction.Transaction;
import com.abc.transaction.TransactionType;
import com.abc.transaction.Withdraw;
import com.abc.util.Constants;
import com.abc.util.Utils;

public abstract class Account {

    private String accountNumber;
    private BigDecimal accountBalance;
    private Date openingDate;
    private Date endDateForInterestCalc;
    private Date lastWithdrawalDate;
    private List<Transaction> transactions;

    public Account() {
    	setOpeningDate(Utils.getNow());
    	accountBalance = BigDecimal.ZERO;
        transactions = new ArrayList<Transaction>();
    }
    
    public Account(String accountNumber) {
    	this();
    	this.accountNumber = accountNumber;
    }
    
    public synchronized void processTransferTransaction(BigDecimal amount, TransactionType transType, Account toAccount) throws InvalidTransactionException {
    	if (TransactionType.TRANSFER != transType) {
    		throw new InvalidTransactionException(Constants.INVALID_TRANSFER_ERROR);
    	}
    	//TODO add a transaction for these 2 events    	
    	//Withdraw from this account
    	Transaction withdrawTrans = new Withdraw(amount);
    	setAccountBalance(withdrawTrans.process(accountBalance));
    	transactions.add(withdrawTrans);
    	
    	//Deposit to toAccount
    	Transaction depositTrans = new Deposit(amount);
    	toAccount.setAccountBalance(depositTrans.process(toAccount.getAccountBalance()));
    	toAccount.getTransactions().add(depositTrans);

    }
    
    public synchronized void processTransaction(BigDecimal amount, TransactionType transType) throws InvalidTransactionException {
		Transaction transaction = null;
		if (TransactionType.DEPOSIT == transType) {
			transaction = new Deposit(amount);
		} else if (TransactionType.WITHDRAW == transType) {
			transaction = new Withdraw(amount);
			setLastWithdrawalDate(transaction.getTransDate());
		} else {
			throw new InvalidTransactionException(Constants.INVALID_DEP_WITH_ERROR);
		}
		setAccountBalance(transaction.process(accountBalance));
		transactions.add(transaction);
    }

    public abstract double getInterestEarned();
    
    public abstract String getAccountType();
    
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getDetailedStatement() {
		
		StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append(Constants.ACCOUNT_NUMBER);
		statementBuilder.append(Constants.FILLER);
		statementBuilder.append(getAccountNumber());
		statementBuilder.append(Constants.FILLER);
		statementBuilder.append(Constants.ACCOUNT_TYPE);
		statementBuilder.append(Constants.FILLER);
		statementBuilder.append(getAccountType());
		statementBuilder.append(Constants.LINE_SEPARATOR);
		statementBuilder.append(Constants.TRANSACTION_LOG);
		statementBuilder.append(Constants.LINE_SEPARATOR);
		for (Transaction trans : getTransactions() ) {
			statementBuilder.append(trans.getStatementListing());
			statementBuilder.append(Constants.LINE_SEPARATOR);
		}
		statementBuilder.append(Constants.TOTAL_FOR_ACCOUNT);
		statementBuilder.append(Constants.FILLER);
		statementBuilder.append(Utils.displayRoundedWithCurrency(getAccountBalance()));
		
		return statementBuilder.toString();
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getLastWithdrawalDate() {
		return lastWithdrawalDate;
	}

	public void setLastWithdrawalDate(Date lastWithdrawalDate) {
		this.lastWithdrawalDate = lastWithdrawalDate;
	}

	public Date getEndDateForInterestCalc() {
		if ( endDateForInterestCalc!= null ) {
			return endDateForInterestCalc;
		} else {
			return Utils.getNow();
		}
	}

	public void setEndDateForInterestCalc(Date endDateForInterestCalc) {
		this.endDateForInterestCalc = endDateForInterestCalc;
	}
	
	
}
