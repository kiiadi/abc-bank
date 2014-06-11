package com.abc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * A registered bank customer who can open accounts with the bank.
 */
public interface Customer {

    String getId();

    /** Gets the customer name.
     */
    String getName();

    /**
     * Adds a new acccount to the customer.
     * @param account the account to add
     * @throws java.lang.IllegalArgumentException if the account is already added
     */
    void addAccount(Account account);

    /**
     * Gets all accounts for the customer.
     * @return list of accounts
     */
    List<Account> getAccounts();

    /**
     * Transfers money between accounts.
     * @param fromAccount the account to move from
     * @param toAccount the account to move to
     * @param amount the amount to move
     * @param txnDate the transaction date
     * @throws java.lang.IllegalArgumentException if either account does not belong to this customer
     */
    void transfer(Account fromAccount, Account toAccount, BigDecimal amount, Date txnDate);

    /**
     * Creates a statement, using the configured statement creator.
     * @return the statement
     */
    String getStatement();
}
