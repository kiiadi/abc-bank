
package com.abc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class BaseAccount implements Account {
    private double balance;
    private final List<Transaction> transactions;
    private final double interestRate;
    private final int accNumber;
    private final AccountType accountType;
    private final double MAX_WITHDRAWAL_LIMIT;
    private double availableWithdrawalAmount;


    /**
     *
     * @param accOwner Customer opening the account
     * @param accNum Number used to uniquely identify the account
     * @param accType Type of account being opened
     */
    public BaseAccount(int accNum, AccountType accType) {
        this.transactions = new ArrayList<Transaction>();
        this.accNumber = accNum;
        this.accountType = accType;
        this.MAX_WITHDRAWAL_LIMIT = accType.getWithdrawalLimit();
        this.availableWithdrawalAmount = accType.getWithdrawalLimit();
        this.interestRate = accType.getInterestRate();
        this.balance = 0.0;
    }

    public int getAccountNum() {
        return accNumber;
    }
    
    public double getBalance() {
        return balance;
    }

    public synchronized void withdraw(double amount, TransactionType type) {
        balance = balance - amount;
        availableWithdrawalAmount = availableWithdrawalAmount- amount;
        addTransaction(new Date(), type, amount);
    }

    public  synchronized void deposit(double amount, TransactionType type) {
        balance = balance + amount;
        addTransaction(new Date(), type, amount);
    }

   
    public String getAccType() {
        return accountType.toString();
    }

 
    public double getInterestRate() {
        return interestRate;
    }

   
    public void addTransaction(Date d, TransactionType trans_Type, double amount) {
        transactions.add(new Transaction(d, trans_Type, amount));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getRemainingWithdrawalLimit() {
        return availableWithdrawalAmount;
    }

    public double getMaximumWithdrawalLimit() {
        return MAX_WITHDRAWAL_LIMIT;
    }

    public void resetAvailableWithdrawalAmount() {
        availableWithdrawalAmount = MAX_WITHDRAWAL_LIMIT;
    }

    public String getAccountSummary() {
        return "Account Numer: " + this.accNumber
                + "\n Account Type: " + this.accountType.toString()
                + "\n Interest Rate: " + this.accountType.getInterestRate()
                + "\n Withdrawl Rate: " + this.accountType.getWithdrawalLimit();
         
    }
    
    boolean findTransactionsByDateAndType(AccountType typ){
    	boolean flag = false;
       
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -10);
        Date todate1 = cal.getTime();    
        int count = 0;
       
     
    	
    	  for (Transaction t : this.getTransactions()) {
    		  
    	        if (todate1.compareTo(t.getTransactionDate())>0){
    	            count = count +1;
    	        }
    	  }
    	  if (count == 0){
    		  flag = true;
    	  }
    	
    	return flag;
    }
    
    
    public double interestEarned() {
        double amount = sumTransactions();
  
   if(this.getAccType().equalsIgnoreCase("0")){
	   if (amount <= 1000)
           return amount * 0.001;
       else
           return 1 + (amount-1000) * 0.002;
   }
   
   else if(this.getAccType().equalsIgnoreCase("2")){
	   
	   if (findTransactionsByDateAndType(this.accountType)){
		   return amount * 0.005;
	   }else {
		   return amount *0.001;
	   }
   } else {
	   return amount * 0.001;
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

  



   
    }