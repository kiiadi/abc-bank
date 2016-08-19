//package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
        
        //System.out.println("deposit: " +amount +", sum: " + sumTransactions());
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
    }
    //System.out.println("withdraw: " +amount +", sum: " + sumTransactions());

}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
        	//case CHECKING:
            //        return amount * 0.001;    		
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + ((amount-1000) * 0.002);
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;      
            case MAXI_SAVINGS:
            	if(checkWithdrawDates() == true){
                    return amount * 0.05;
            	}
            	else
            {
                if (amount <= 1000)
                    return amount * 0.02;
                else if (amount > 1000 && amount< 2000)
                    return 20 + ((amount-1000) * 0.05);
                else if (amount > 2000)
                	return 70 + ((amount-2000) * 0.1);
            }
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }
    

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        //System.out.print("check : ");
        
        for (Transaction t: transactions){
        	Assert.assertNotNull(t);
            amount += t.amount;
            //System.out.print(t.amount);
        }
        return amount;
    }

    private boolean checkWithdrawDates() {
        //System.out.print("check : ");
        int count=0;
        for (Transaction t: transactions){
        	// should check the dates
        	int timediff = (int)((t.getDate().getTime() - (new java.util.Date().getTime())) / (1000 * 60 * 60 * 24));
            System.out.print("check date : " + timediff);
        	if(t.amount < 0 && timediff < 10)
        		return false;
        	if(t.amount >0){
        		count++;
        	}
        }
        if(count == transactions.size())
        	return false;
        else
        	return true;
    }
    
    public int getAccountType() {
        return accountType;
    }

}
