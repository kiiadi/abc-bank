package com.abc;

import com.abc.interest.InterestStrategy;
import com.abc.interest.InterestStrategyFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final AccountType accountType;
    private final List<Transaction> transactions;
    private final InterestStrategy interestStrategy;

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.interestStrategy = InterestStrategyFactory.getInstance().getStrategy(accountType);
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(BigDecimal amount) {
        Utils.ensurePositive(amount);
        transactions.add(new Transaction(amount));
    }

    public void withdraw(BigDecimal amount) {
        Utils.ensurePositive(amount);
        transactions.add(new Transaction(amount.negate()));
    }

    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions();
        return interestStrategy.interestEarned(amount, getTransactions());
    }

    public BigDecimal sumTransactions() {
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        for (Transaction t : transactions) {
            amount = amount.add(t.getAmount());
        }
        return amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountType=" + accountType +
                '}';
    }
}
