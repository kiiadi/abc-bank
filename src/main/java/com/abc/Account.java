package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * Accounts Class. Dependent on Customer
 */
public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    private List<Transaction> transactions;

    private double balance; // Account Balance is maintained by this variable

    public Account(int accountType) {
        this.accountType = accountType;
        validateAccountType();

        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * Deposits the Amount and readjusted the Balance
     * @param amount
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            balance +=amount;
            transactions.add(new Transaction(amount));
        }
    }

    /**
     * Withdraws amount from Account and readjusted the balance
     * @param amount
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        else if(balance < amount){
            throw new IllegalArgumentException("Insufficient Balance in the Account");
        }
        else {
            balance -= amount;
            transactions.add(new Transaction(-amount));
        }
    }

    /**
     * Calcuates the interest earned based on the Account Type
     * @return
     */
    public double interestEarned() {
        double amount = balance;
        switch(accountType){
            case SAVINGS:
                return calculateSavingsAccInterest();

            case MAXI_SAVINGS:
                 return calculateMaxiSavingsAccInterest();

            default:
                return amount * 0.001;
        }
    }

    /**
     * Validates the Account Type whether it matches any of the final
     * constants described in the beginning of the class.
     * @return
     */
    private boolean validateAccountType(){
        if(accountType < 0 || accountType > 3){
            throw new IllegalArgumentException("Account Type is Invalid");
        }
        return true;
    }

    /**
     * Calculates the Savings Account Interest based on the Formula
     *          Amount <1000  - 0.1% Interest Rate
     *          Amount >1000  - 0.2% Interest Rate
     * @return
     */
    private double calculateSavingsAccInterest(){
        if (balance <= 1000)
            return balance * 0.001;
        else
            return 1 + (balance-1000) * 0.002;
    }


    /**
     * Calculates the Maxi Savings account interest based on the formula
     *          Amount <1000           - 2% Interest Rate
     *          Amount >1000 & < 2000  - 5% Interest Rate
     *          Amount >2000           - 10% Interest Rate
     * @return
     */
    /*
    private double calculateMaxiSavingsAccInterest(){
        if (balance <= 1000)
            return balance * 0.02;
        if (balance <= 2000)
            return 20 + (balance-1000) * 0.05;
        return 70 + (balance-2000) * 0.1;
    } */

    /**
     * Calculates the Maxi Savings account interest based on the formula
     *          No withdrawl within 10 Transactions  - 5% Interest Rate
     *          Withdrwal with 10 Transactions       - 0.1% Interest Rate
     * @return
     */
    private double calculateMaxiSavingsAccInterest(){
        int lastWithdrawalIdx = findLastWithdrawalTransactionIdx();

        if(transactions.size() - lastWithdrawalIdx+1 >=10){
            return balance * 0.05;
        }
        else{
            return balance * 0.001;
        }
    }

    /**
     * Finds index with the Transactions list where in last withdrawal happened
     * @return
     */
    private int findLastWithdrawalTransactionIdx(){
        int idx =transactions.size();
        Transaction tx = null;
        while (--idx > -1){
            tx = transactions.get(idx);
            if(tx.getAmount()<0){
                break;
            }
        }
        return idx;
    }

    /**
     * Gets Account Balance
     * @return
     */
    public double getAccountBalance(){
        return balance;
    }

    /**
     * Gets Account Type
     * @return
     */
    public int getAccountType() {
        return accountType;
    }

    /**
     * Gets list of transactions for the Account
     * @return
     */
    public List<Transaction> getTransactions(){
        return transactions;
    }

}
