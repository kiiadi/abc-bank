package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account {

    public abstract double interestEarned();

    static enum AccountType {
        CHECKING(0, "Checking"),
        SAVINGS(1, "Savings"),
        MAXI_SAVINGS(2, "Maxi-Savings"),
        UNKNOWN(-1, "Unknown");

        private int value;
        private String desc;

        AccountType(final int value, final String desc) {
            this.value = value;
            this.desc = desc;
        }

        int getValue() {
            return value;
        }

        String getDesc() {
            return desc;
        }

        public static AccountType fromValue(final int value) {
            for (final AccountType accountType : AccountType.values()) {
                if (accountType.getValue() == value) {
                    return accountType;
                }
            }
            return AccountType.UNKNOWN;
        }

    }

    private final AccountType accountType;
    private List<Transaction> transactions;
    private Date openDate;

    public Account() {
        accountType = AccountType.UNKNOWN;
    }

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public Account(AccountType accountType, double initialDeposit) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();

        deposit(initialDeposit);
    }

    public Account(AccountType accountType, double initialDeposit, Date openDate) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.openDate = openDate;

        deposit(initialDeposit);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else if (amount > this.balance()){
            throw new InsufficientFundsException("amount exceeds available balance");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public void transferFrom(Account fromAccount, double amount){
        fromAccount.withdraw(amount);
        deposit(amount);
    }



    public double balance() {
        double amount = 0.0;
        if (transactions != null) {
            for (Transaction t : transactions)
                amount += t.amount;
        }
        return amount;
    }

    public Transaction latestTransaction(){
        Transaction latest = null;

        for(Transaction transaction : transactions){
            if (latest == null || transaction.getTransactionDate().compareTo(latest.getTransactionDate()) > 0){
                latest = transaction;
            }
        }

        return latest;
    }

    public Transaction latestWithdrawal(){
        Transaction latestWithdrawal = null;

        for(Transaction transaction : transactions){
            if ((transaction.amount<0 && latestWithdrawal == null) ||
                (transaction.amount<0 && transaction.getTransactionDate().compareTo(
                        latestWithdrawal.getTransactionDate()) > 0)){
                latestWithdrawal = transaction;
            }
        }

        return latestWithdrawal;
    }

    public AccountType accountType() {
        return accountType;
    }

    public List<Transaction> transactions() {
        return transactions;
    }

    public Date openDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate){
        this.openDate = openDate;
    }

}
