package com.abc.accounts;

import com.abc.Transaction;
import com.abc.util.ReportFormatterHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

abstract class AbstractAccount implements Account{

    private List<Transaction> transactions = new ArrayList<Transaction>();

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, Calendar.getInstance().getTime()));
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount, Calendar.getInstance().getTime()));
        }
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.getAmount();
        return amount;
    }

    @Override
    public String getStatement() {
        StringBuilder statement = new StringBuilder(getAccountDescription()+"\n");

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction transaction : transactions) {
            statement.append("  ").append(transaction.getAmount() < 0 ? "withdrawal" : "deposit").append(" ").append(ReportFormatterHelper.toDollars(transaction.getAmount()) + "\n");
            total += transaction.getAmount();
        }
        statement.append("Total ").append(ReportFormatterHelper.toDollars(total));
        return statement.toString();
    }

    @Override
    public void transferTo(Account toAccount, double amount){
        this.withdraw(amount);
        toAccount.deposit(amount);
    }

    protected List<Transaction> getTransactions() {
        return transactions;
    }

    public abstract double interestEarned();
    public abstract String getAccountDescription();

}
