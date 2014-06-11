package com.abc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * An account belonging to a customer.
 */
public interface Account {

    /**
     * Gets the unique id.
     * @return
     */
    String getId();

    /**
     * Gets the type.
     * @return
     */
    AccountType getAccountType();

    /**
     * Deposits into the account.
     * @param amount the amount to deposit
     * @param txnDate the transaction date
     */
    void deposit(BigDecimal amount, Date txnDate);

    /**
     * Withdraws from the account.
     * @param amount the amount to deposit
     * @param txnDate the transaction date
     */
    void withdraw(BigDecimal amount, Date txnDate);

    /**
     * Gets the amount of interest earned up to the given date.
     * @param toDate the end date
     * @return the interest
     */
    BigDecimal getInterestEarned(Date toDate);

    /**
     * Gets the account balance.
     * @return
     */
    BigDecimal getBalance();

    /**
     * Gets all transactions.
     * @return the transaction list
     */
    List<Transaction> getTransactions();

    /**
     * Creates a statement, using the configured statement creator.
     * @return the statement
     */
    String getStatement();
}
