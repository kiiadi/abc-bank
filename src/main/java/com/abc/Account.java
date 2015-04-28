package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;
    private Date lastDeposit;
    private Date lastWithdrawal;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        lastDeposit = lastWithdrawal = DateProvider.getInstance().now();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
            lastDeposit = DateProvider.getInstance().now();
        }
    }
    //Just made this to test the date variables. If they are recording properly then that suggests that the maxi-function operates correctly.
    public String getDepositDate(){
    	return lastDeposit.toString();
    }
    
    public String getWithdrawalDate(){
    	return lastWithdrawal.toString();
    }
    //Remove after testing. This is just to test the maxi-savings over 10 days
    public void setWithdrawlDate(Date date){
    	lastWithdrawal = date;
    }
    
public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
        lastWithdrawal = DateProvider.getInstance().now();
    }
}
//I added in the accrue daily rates. This method will now return just the interest for 1 day rather than a year.
//In the future the program needs a timer so that everyday at a given time this method is called and the daily interest
//is added to each customer's account.

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                	//0.01% for first 1000 per-annum
                    return amount * (0.001/365);
                else
                	//0.2% after first 1000 per-annum
                    return 1 + (amount-1000) * (0.002/365);
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
            	Date currentDate = DateProvider.getInstance().now();
            	//5% per annum after 10 days without withdrawing
            	if(currentDate.compareTo(lastWithdrawal)>=10){
            		return amount * (0.05/365);
            	}
            	//.1% per annum if the customer withdrew in the past 10 days
            	return amount * (0.001/365);
            	//Previous code for the MAXI_SAVINGS account interest calculation.
                /*if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;*/
            default:
                return amount * (0.001/365);
        }
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

    public int getAccountType() {
        return accountType;
    }
    
}
