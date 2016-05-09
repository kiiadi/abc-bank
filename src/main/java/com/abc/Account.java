package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class Account {
    /**
     * The abstract account entity to represent the various types of accounts that can be opened
     * All the implementations of this abstract account class have to provide definitions for the
     * calculateInterest() method
     */

    //the various account types
    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    private List<Transaction> transactions;
    private Date accountOpenDate;

    /**
     * Constructor to initialize the account object by specifying the account type
     * @param accountType the type of account to be opened
     */
    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.accountOpenDate = Calendar.getInstance().getTime();
    }

    /**
     * Getter method to list all the transactions that happen in the account
     * @return
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * The abstract method that every sub-class has to implement
     * @return the interest calculated by that account
     */
    public abstract double calculateInterest();

    /**
     * Returns the balance in the account. If there are no deposits, the balance is 0
     * @return the account balance
     */
    public double getAccountBalance() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    /**
     * Returns the last transaction that is an account withdrawal.
     * A withdrawal is identified by the negative amount in the transaction
     * This is useful for calculating the interest based on the withdrawal activity in the account.
     * @return the transaction object containing the withdrawal. If there are no withdrawals, then null
     */
    public Transaction getLastWithdrawalTransaction() {
        int index = transactions.size() - 1;
        while (index >= 0) {
            if (transactions.get(index).getAmount() < 0) {
                return transactions.get(index);
            }
            index--;
        }
        return null;
    }

    /**
     * Getter method for the account type
     * @return the account type
     */
    public int getAccountType() {
        return accountType;
    }

    /**
     * Getter method for the account open date
     * @return the account open date
     */
    public Date getAccountOpenDate() {
        return accountOpenDate;
    }

    /**
     * Setter method for the account open date. This is useful for testing purposes
     * @param date the date at which the account was opened
     */
    public void setAccountOpenDate(Date date) {
        this.accountOpenDate = date;
    }

    /**
     * Returns the difference between the account open date and now.
     * This is useful for calculating the interest based on when the account was opened
     * @return the number of days interest has accrued in the account
     */
    public long getNumOfDaysForInterestAccrual() {
        return Math.abs((getAccountOpenDate().getTime() - Calendar.getInstance().getTime().getTime())/(24*60*60*1000));
    }

}
