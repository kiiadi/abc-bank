package com.abc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.abc.util.Utils;

public class Account {

    private final AccountType accountType;
    private final int accountNumber;
	// list will store sorted transactions, oldest first.
    private List<Transaction> transactions;

    public Account(AccountType accountType, int accountNumber) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.transactions = new LinkedList<Transaction>();
    }

    public void deposit(double amount) {
    	deposit(amount, System.currentTimeMillis());
    }

    public void deposit(double amount, long depositMs) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, new Date(depositMs)));
            Collections.sort(transactions);
        }
    }

	public void withdraw(double amount) {
    	withdraw(amount, System.currentTimeMillis());
	}
	
	public void withdraw(double amount, long timeMs) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	        transactions.add(new Transaction(-amount, new Date(timeMs)));
	        Collections.sort(transactions);
	    }
	}
	
    public double interestEarned() {
		double interest = 0.0;
		ArrayList<DailyBalance> dailyBalanceList = new ArrayList<DailyBalance>();

		for(int i = 0; i < transactions.size(); i++){
			Transaction previous	= null;
			Transaction current		= transactions.get(i);
			Transaction next		= (transactions.size() > i + 1) ? transactions.get(i + 1) : null;

			double balance = Math.max(0, current.amount);
			
			if(i > 0){
				previous = transactions.get(i - 1);
				balance += previous.amount;
			}
			
			long days = 0;
			if(next != null){
				days = Utils.getInstance().daysBetween(current.getTransactionDateTime(), next.getTransactionDateTime());
			}
			else{
				days = Utils.getInstance().daysBetween(current.getTransactionDateTime(), System.currentTimeMillis());
			}
			dailyBalanceList.add(new DailyBalance((int)days, balance));
			
		}

		for(DailyBalance dailyBalance : dailyBalanceList){
			interest += calculateInterest(dailyBalance);
		}

		return interest;
	}
	
	private double calculateInterest(DailyBalance dailyBalance){
		double amount = dailyBalance.getBalance();
		double interest = 0.0;
        switch(accountType){
        case SAVINGS:
            if (amount <= 1000)
                interest = amount * Constants.SAVING_RATE_FOR_1K / 365;
            else
                interest = (1000 * Constants.SAVING_RATE_FOR_1K / 365) + ((amount-1000) * Constants.SAVING_RATE / 365);
            break;

        case MAXI_SAVINGS:
        	// Change Maxi-Savings accounts to have an interest rate of 5%
        	// assuming no withdrawals in the past 10 days otherwise 0.1%
            if (getDaysAfterLastTransaction() > Constants.MAXI_DAYS_WO_WITHDRAWALS_FOR_HIGH_RATE)
                interest = amount * Constants.MAXI_HIGH_RATE / 365;
            else
            	interest = amount * Constants.MAXI_RATE / 365;
            break;

        default:
        	interest = amount * Constants.CHECKING_RATE / 365;
        }
        
        interest = interest * dailyBalance.getNumberOfDays();
        
		return Utils.getInstance().roundDouble(interest);
	}

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }
    
    /**
     * 
     * @return the number of days passed after last transaction on this account
     */
    public int getDaysAfterLastTransaction(){

    	// find milliseconds passed after last transaction
    	long nowMs = System.currentTimeMillis();
        long lastMs = nowMs;

        for (Transaction t: transactions){
        	if(t.getTransactionDateTime() < lastMs)
        		lastMs = t.getTransactionDateTime();
        }
        
        // convert ms in to days
        int numberOfDays = (int) (nowMs - lastMs) / (1000*60*60*24);
        
        return numberOfDays;
    }

	public int getAccountNumber() {
		return accountNumber;
	}
	
	public Collection<Transaction> getTransactions(){
		return Collections.unmodifiableCollection(transactions);
	}

}
