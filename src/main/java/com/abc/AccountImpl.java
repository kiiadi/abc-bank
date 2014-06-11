package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * See {@link com.abc.Account}
 *
 * Note: Assuming no overdraft limit so currently accounts can go negative.
 */
public class AccountImpl implements Account {

    private String id;

    private AccountType accountType;

    private InterestCalculator interestCalculator;

    private final List<Transaction> transactions = new ArrayList<Transaction>();

    private AccountStatementCreator statementCreator;


    public AccountImpl(final String id,
                       final AccountType accountType,
                       final InterestCalculator interestCalculator,
                       final AccountStatementCreator statementCreator) {
        this.id = id;
        this.accountType = accountType;
        this.interestCalculator = interestCalculator;
        this.statementCreator = statementCreator;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public void deposit(final BigDecimal amount, final Date txnDate) {
        makeTransaction(amount, txnDate);
    }

    @Override
    public void withdraw(final BigDecimal amount, final Date txnDate) {
        makeTransaction(amount.negate(), txnDate);
    }


    @Override
    public BigDecimal getInterestEarned(final Date periodEndDate) {
        return interestCalculator.getInterestAccrued(periodEndDate);
    }

    @Override
    public BigDecimal getBalance() {
        if (transactions.isEmpty()) {
            return BigDecimal.ZERO;
        }
        else {
            return transactions.get(transactions.size() - 1).getResultingBalance();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountImpl)) {
            return false;
        }

        AccountImpl account = (AccountImpl) o;

        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String getStatement() {
        return statementCreator.create(this);
    }


    Integer getDaysSinceLastWithdrawal(final Date date) {
        Date lastWithdrawal = null;

        for (Transaction transaction : transactions) {
            if (transaction.getTransactionDate().compareTo(date) > 0) {
                break;
            }
            if (transaction.isWithdrawal()) {
                lastWithdrawal = transaction.getTransactionDate();
            }
        }

        return lastWithdrawal == null ? null : DateUtils.getDaysBetween(lastWithdrawal, date);
    }

    BigDecimal getBalance(final Date date) {
        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            if (transaction.getTransactionDate().compareTo(date) > 0) {
                break;
            }
            balance = transaction.getResultingBalance();
        }

        return balance;
    }

    private void makeTransaction(final BigDecimal amount, final Date txnDate) {

        if (transactions.isEmpty()) {
            transactions.add(new Transaction(amount, txnDate, amount));
        }
        else {
            Transaction lastTransaction = transactions.get(transactions.size() - 1);
            transactions.add(new Transaction(amount, txnDate, lastTransaction.getResultingBalance().add(amount)));
        }
    }
}
