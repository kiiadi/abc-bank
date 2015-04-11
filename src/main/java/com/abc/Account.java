package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    public static final BigDecimal NUM_OF_DAYS_YEAR = new BigDecimal("365");

    private String accountNumber;
    private final int accountType;
    private List<Transaction> transactions;

    public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Account(int accountType, String accountNumber) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.transactions = new ArrayList<Transaction>();
    }

    public synchronized void deposit(BigDecimal amount) {
    	if (amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, "Deposit"));
        }
    }
	
	public synchronized void withdraw(BigDecimal amount) throws Exception {
    	if (amount.compareTo(BigDecimal.ZERO) <= 0){
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	    	BigDecimal currentBalance = sumTransactions();
	    	if(currentBalance.compareTo(amount) < 0){
		        throw new Exception("requested amount for withdrawal can not be more then current blance");
	    	}
	    	
	        transactions.add(new Transaction(amount.negate(), "Withdraw"));
	    }
	}

	//the default interest
	public BigDecimal interestEarned(){
		BigDecimal amount = sumTransactions();
		return amount.multiply(new BigDecimal("0.001"));
	}


    public BigDecimal sumTransactions() {
		BigDecimal amount = BigDecimal.ZERO;
		for (Transaction t: transactions)
		    amount = amount.add(t.getAmount());
		return amount;
    }

    private boolean checkIfTransactionsExist(boolean checkAll) {
    	if(transactions.size()>0)
    		return true;
    	
    	return false;
    }

    public int getAccountType() {
        return accountType;
    }

    public Transaction getLastWithdrawlTransaction(){
		BigDecimal zero = BigDecimal.ZERO;
		for (Transaction t: transactions){
		    if(t.getAmount().compareTo(zero)<0){
		    	return t;
		    }
		}
		
		return null;
    }
}
