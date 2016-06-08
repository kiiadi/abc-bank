package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

  
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        try {
            customers = null;
            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
    
    public BaseAccount findAccountByCustomer(Customer cust,int accnum){
    	BaseAccount account = null;
    	if(cust != null){
    		 for(BaseAccount a:  cust.getAccounts())
    		 {
    		       if( a.getAccountNum() == accnum){
    		    	   account = a;
    		       }
    	}
    	}
    	return account;
    	
    }
       
    
    
    public TransactionStatus makeDeposit(Customer cust, int accNum, double amount) {
        //Check if account exists
    	BaseAccount ba = this.findAccountByCustomer(cust,accNum);
    	
        if (ba != null) {
        	ba.deposit(amount, TransactionType.DEPOSIT);
        	return new TransactionStatus(false, Messages.CONFIRMATION_COMPLETE.toString());
        } else {
            //Return not found error
            return new TransactionStatus(false, Messages.ERROR_ACCOUNT_NOT_FOUND.toString());
        }
    }

   
    public TransactionStatus makeWithdrawal(Customer cust, int accNum, double amount) {
    	
    	BaseAccount ba = this.findAccountByCustomer(cust,accNum);
        if( amount < 0 ) {
        	
            return new TransactionStatus(false, Messages.ERROR_WITHDRAWAL_AMOUNT_NEGATIVE.toString());
        }
        //Calculate what the new balance would be
        double newBalance = ba.getBalance()- amount;
        
        if (ba != null) {

            if (amount <= ba.getRemainingWithdrawalLimit()) {
            
                if (amount <= ba.getBalance()) {

                   ba.withdraw(-amount, TransactionType.WITHDRAW);

                } else {
       
                    return new TransactionStatus(false, Messages.ERROR_INSUFFICIENT_FUNDS.toString());
                }

            } else {
     
                return new TransactionStatus(false, Messages.ERROR_ABOVE_WITHDRAWAL_LIMIT.toString() + " Available amount: £" + ba.getRemainingWithdrawalLimit());
            }
        } else {
            return new TransactionStatus(false, Messages.ERROR_ACCOUNT_NOT_FOUND.toString());
        }

       
        return new TransactionStatus(true, Messages.CONFIRMATION_WITHDRAWAL.toString());
    }

  
    public TransactionStatus makeTransfer(Customer cust,int sourceAccNum, int destinationAccNum, double amount) {
       
      
        TransactionStatus withdrawOperation = makeWithdrawal(cust,sourceAccNum, amount);
      

        if (withdrawOperation.isCompleted()) {
        	BaseAccount destAcct = this.findAccountByCustomer(cust,destinationAccNum);
            if (destAcct != null) {
                makeDeposit(cust,destAcct.getAccountNum(), amount);
            } else {
            	BaseAccount srcAcct = this.findAccountByCustomer(cust,sourceAccNum);
            	makeDeposit(cust,srcAcct.getAccountNum(), amount);
                
                //Return error stating destination could not be found.
                return new TransactionStatus(false, Messages.ERROR_DEST_ACC_NOT_FOUND.toString());
            }

        } else {
            //Return why the withdrawal failed
            return withdrawOperation;
        }
        //Confirm success
        return new TransactionStatus(true, Messages.CONFIRMATION_TRANSFER.toString());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

  
}
