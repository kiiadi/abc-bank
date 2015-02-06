package com.abc.service;

import static com.abc.utils.InterestRules.getDefaultInterest;
import static com.abc.utils.InterestRules.getInterestOnMaxiSavingsAccount;
import static com.abc.utils.InterestRules.getInterestOnSavingsAccount;
import static com.abc.domain.AccountType.MAXI_SAVINGS;
import static com.abc.utils.Utils.EOL;
import static com.abc.utils.Utils.toDollars;
import static com.abc.service.TransactionService.withdrawl;
import static com.abc.service.TransactionService.within10days;

import java.util.List;

import com.abc.domain.Account;
import com.abc.domain.Transaction;

public class AccountService {
	
	private TransactionService transactionService;
	
	public void deposit(Account account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        } else {
        	account.getTransactions().add(new Transaction(amount));
        }
    }

	public void withdraw(Account account, double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		} else {
			account.getTransactions().add(new Transaction(-amount));
		}
	}
	
    public double getInterestEarned(Account account, boolean useSpecialIntrestRate) {
    	
        double amount = sumTransactions(account);
        switch(account.getAccountType()){
            case SAVINGS:
                return getInterestOnSavingsAccount(amount);
            //case SUPER_SAVINGS:
            //	return getInterestOnSuperSavingsAccount(amount);
            case MAXI_SAVINGS:
                return getInterestOnMaxiSavingsAccount(amount, useSpecialIntrestRate);
            default:
                return getDefaultInterest(amount);
        }
    }

    public double sumTransactions(Account account) {
    	return account.getTransactions()
    		    .stream()
    		    .mapToDouble(Transaction::getAmount)
    		    .sum();
    }
    
    public String getStatement(Account account) {
    	if(account == null) {
    		throw new IllegalArgumentException("Attempt to get statement for null account.");
    	}
    	
        StringBuilder s = new StringBuilder();
        s.append(account.getAccountType().getType()).append(" Account").append(EOL);

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction transaction : account.transactions) {
            s.append(transactionService.getTransactionDetails(transaction)).append(EOL);
            total += transaction.getAmount();
        }
        s.append("Total ").append(toDollars(total));
        return s.toString();
    }

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public boolean isSpecialInterestRateApplicable(Account account) {
		if(MAXI_SAVINGS.equals(account.getAccountType()) && noWithdrawlInLast10Days(account.getTransactions())) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean noWithdrawlInLast10Days(List<Transaction> transactions) {
		for(Transaction transaction: transactions) {
			if( withdrawl(transaction) && within10days(transaction) ) {
				return false;
			}
		}
		return true;
	}
}
	