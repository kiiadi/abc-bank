package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    public List<Transaction> transactions;

    protected Account()
    {
        this.transactions = new CopyOnWriteArrayList<Transaction>();
    }

    public static Account getAccount(int accountType){
        switch (accountType) {
            case SAVINGS:
                return new SavingsAccount();
            case CHECKING:
                return new CheckingAccount();
            default:
                return new MaxiSavingsAccount();
        }
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount.negate()));
        }
    }

    public abstract BigDecimal interestEarned();

    public BigDecimal sumTransactions() {
        return checkIfTransactionsExist(true);
    }

    private BigDecimal checkIfTransactionsExist(boolean checkAll) {
        BigDecimal amount = BigDecimal.ZERO;
        for (Transaction t : transactions)
            amount = amount.add(t.amount);
        return amount;
    }

    public abstract int getAccountType();

}
