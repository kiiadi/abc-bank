package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void deposit(double amount) throws AbcBankException {
        if (amount <= 0) {
            throw new AbcBankException(BankConstant.AMOUNT_EXCEPTION.type(),"ILLEGAL_ARGUMENT_EXCEPTION");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) throws AbcBankException {
        if (amount <= 0) {
            throw new AbcBankException(BankConstant.AMOUNT_EXCEPTION.type(),"ILLEGAL_ARGUMENT_EXCEPTION");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
                Date currentDate = new Date();
                boolean withdrawalsInPastTenDays = false;
                for(Transaction transaction : transactions){
                    Date transactionDate = transaction.getTransactionDate();
                    String transactionType = transaction.amount < 0 ? BankConstant.WITHDRAWAL.type() : BankConstant.DEPOSIT.type();
                    long days = getDifferenceDays(currentDate,transactionDate);
                    if(days<=10 && BankConstant.WITHDRAWAL.type().equals(transactionType)){
                        withdrawalsInPastTenDays = true;
                        break;
                    }
                }
                if(withdrawalsInPastTenDays){
                    return amount * 0.001;
                }else{
                    return amount * 0.05;
                }
            default:
                return amount * 0.001;
        }
    }

    public long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
