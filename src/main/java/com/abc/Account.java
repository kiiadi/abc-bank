package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Account {
	
	public static class AccountNumber {
	 	
		private static int AccountNumber =1 ;
		
		public static int getPreviousAcctNumber(){
	    	return AccountNumber;
	    }
	    
	    public static void setNewAccountNumber(int acctNo){
	    	AccountNumber= acctNo;
	    }
	}

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    
    // added private variable dateInterestedAccruedLast and accountNumber
    private Date dateInterestAccruedLast;
    private int accountNumber;
    private final int accountType;
    public List<Transaction> transactions;
    
    public Account(int accountType) {
        this.accountType = accountType;
        this.accountNumber= AccountNumber.getPreviousAcctNumber()+1;
        AccountNumber.setNewAccountNumber(this.accountNumber);
        this.dateInterestAccruedLast= DateProvider.getInstance().now();
        this.transactions = new ArrayList<Transaction>();
    }
    
    public int getAccountNumber(){
    	return accountNumber;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }
    
    /*Test added to ensure withdrawal is not greater than existing balance*/
  
    public void withdraw(double amount) {
    	
    	if (amount <= 0) {
    		throw new IllegalArgumentException("amount must be greater than zero");
    	}else if (amount >= this.sumTransactions()){
    		throw new IllegalArgumentException("Amount withdrawn should not be greater than existing balance");
    	}else {
    		transactions.add(new Transaction(-amount));
    	}
    }
    
   
    private long getDaysInterestAccrued(){
    	
    	long d1= this.dateInterestAccruedLast.getTime();
    	long d2= DateProvider.getInstance().now().getTime();
    	
    	return Math.abs((d2-d1)/(24*60*60*1000));
    }
    
    public double interestEarned() {
        double amount = sumTransactions();
        
        long daysInterestAccrued= getDaysInterestAccrued();
        
        switch(this.accountType){
        	
        	case CHECKING:
        		return (amount*0.001)*(daysInterestAccrued/365);
        	case SAVINGS:
                if (amount <= 1000)
                    return (amount * 0.001)*daysInterestAccrued/365;
                else
                    return (1000*.001)*daysInterestAccrued/365 + ((amount-1000) * 0.002)*daysInterestAccrued/365;
//           case SUPER_SAVINGS:
//              if (amount <= 4000)
//                  return 20;
            case MAXI_SAVINGS:
                if (!this.checkWithdrawalsForTenDays())
                    return (amount * 0.05)*daysInterestAccrued/365;
                else
                	return (amount *0.001)*daysInterestAccrued/365;
            default:
                return amount * 0.001;
        }
    }
    
    public void setInterestDaysAccrued(Date dateInterestaccruedLast){
    	this.dateInterestAccruedLast= dateInterestaccruedLast;
    }
    
    // boolean function which return true of false with regard to if a withdrawal has happened in the last 10 days
    private boolean checkWithdrawalsForTenDays() {
    	
    	for(Transaction t:this.transactions){
    		
    		if(t.getAmount()<0){
    			long dateOfTransaction = t.getTranscationDate().getTime();
    			DateProvider dt = new DateProvider();
    			long dateToday= dt.now().getTime();
    			if((Math.abs(dateToday-dateOfTransaction)/1000*24*60*60)>10)
    				return true;
    		}
    		
    	}
    	return false;
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);	
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
