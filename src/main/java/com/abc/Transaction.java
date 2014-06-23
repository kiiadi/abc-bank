package com.abc;

import java.util.Date;

public class Transaction {

    private final long ONE_HOUR = 60 * 60 * 1000L;
    private final double amount;
    private final boolean isDeposit;
    
    private long depositAge = 0;
    private long withdrawAge = 0;
    private Date transactionDate = null;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
        
        if(amount > 0){
            this.isDeposit = true;
        }else{
            this.isDeposit = false;
        }
    }

    public double getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public long getDepositAge() {
        depositAge = 0;
        if (amount > 0) {
            Date today = DateProvider.getInstance().now();
            depositAge = 1 + (today.getTime() 
                           - transactionDate.getTime() + ONE_HOUR)
                           / (ONE_HOUR * 24);
        }
        return depositAge;
    }
    
    public long getWithdrawAge(){
        withdrawAge = 0;
        if (amount < 0) {
            Date today = DateProvider.getInstance().now();
            withdrawAge = 1 + (today.getTime() 
                            - transactionDate.getTime() + ONE_HOUR)
                            / (ONE_HOUR * 24);
        }
        return withdrawAge;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

}
