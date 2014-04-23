package com.abc.businessobjects;

import com.abc.process.Transaction;
import com.common.utils.DateProvider;
import org.omg.CORBA.INVALID_TRANSACTION;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Account {

    //constants
    private static final double FLAT_RATE_AMOUNT = 1000.0;
    private static final double MAXI_SAVINGS_FIVE_PERCENT_AMOUNT = 2000.0;

    //interest rates
    private static final double DEFAULT_INTEREST_RATE = 0.001;
    private static final double SAVINGS_PERCENT_INTEREST_RATE = 0.002;
    private static final double TWO_PERCENT_INTEREST_RATE = 0.02;
    private static final double FIVE_PERCENT_INTEREST_RATE = 0.05;
    private static final double TEN_PERCENT_INTEREST_RATE = 0.1;


    //fields
    private final AccountType accountType;
    public List<Transaction> transactions;

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public Account(AccountType accountType, List<Transaction> transactions) {
        this.accountType = accountType;
        this.transactions = transactions;
    }

    public void deposit(double amount) {
        addToTransactions(amount, true);
    }

    public void withdraw(double amount) {
        addToTransactions(amount, false);
    }

    private void addToTransactions(double amount, boolean isDeposit) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            double transactionAmount = (isDeposit) ? amount : -amount;
            //if withdrawing ensure there is enough
            if (transactionAmount < 0 && sumTransactions() >= transactionAmount) {
                transactions.add(new Transaction(transactionAmount));
            } else if(transactionAmount > 0) {
                transactions.add(new Transaction(transactionAmount));
            } else {
                //log that transaction could not be made. Optionally could fail
                System.out.println("Account balance in sufficient for withdrawal of : '" + transactionAmount + "'");
            }
        }
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch (accountType) {
            case SAVINGS:
                return calculateSavingsInterest(amount);
            case MAXI_SAVINGS:
                return calculateMaxiSavingsInterest(amount);
            default:
                return (amount * DEFAULT_INTEREST_RATE);
        }
    }

    public double calculateSavingsInterest(double amount) {
        if (amount <= FLAT_RATE_AMOUNT) {
            return (amount * DEFAULT_INTEREST_RATE);
        } else {
            return 1 + (amount - FLAT_RATE_AMOUNT) * SAVINGS_PERCENT_INTEREST_RATE;
        }
    }

    public double calculateMaxiSavingsInterest(double amount) {
        if (!isWithdrawalExistWithinPastDays(10)) {
            return amount * FIVE_PERCENT_INTEREST_RATE;
        } else {
            return amount * DEFAULT_INTEREST_RATE;
        }
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t : transactions) {
            amount += t.amount;
        }
        return amount;
    }

    public double sumTransactionsForInterestCalculation() {
        double amount = 0.0;
        for (Transaction t : transactions) {
            long diffMillis = (t.getTransactionDate().getTime() - DateProvider.getInstance().now().getTime());
            long numDays = (diffMillis/(1000*60*60*24));
            amount += (t.amount * (numDays/365) );
        }
        return amount;
    }

    private boolean isWithdrawalExistWithinPastDays(int numDays) {
        for (Transaction item : transactions) {
            if (item.getAmount() < 0 && isTransactionDateInRange(item.getTransactionDate(), numDays)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTransactionDateInRange(Date date, int numDays) {
        Date endDate = DateProvider.getInstance().now();
        Date startDate = DateProvider.getInstance().incrementDate(endDate, -numDays);

        return !(date.before(startDate) || date.after(endDate));
    }

    public AccountType getAccountType() {
        return accountType;
    }

}
