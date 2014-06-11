package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * See {@link com.abc.Customer}
 */
public class CustomerImpl implements Customer {

    /**
     * A unique id
     */
    private String id;

    /**
     * The customer's name.
     */
    private String name;

    /**
     * The accounts for this customer.
     */
    private final List<Account> accounts = new ArrayList<>();

    private CustomerStatementCreator statementCreator;

    public CustomerImpl(final String id, final String name, final CustomerStatementCreator statementCreator) {
        this.id = id;
        this.name = name;
        this.statementCreator = statementCreator;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addAccount(final Account account) {
        if (accounts.contains(account)) {
            throw new IllegalArgumentException("Cannot add account, already owned by customer");
        }
        accounts.add(account);
    }

    @Override
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public BigDecimal getTotalInterestEarned(Date toDate) {
        BigDecimal total = BigDecimal.ZERO;
        for (Account account : accounts) {
            total = total.add(account.getInterestEarned(toDate));
        }
        return total;
    }

    @Override
    public void transfer(final Account fromAccount,
                         final Account toAccount,
                         final BigDecimal amount,
                         final Date transactionDate) {

        if (fromAccount == toAccount) {
            throw new IllegalArgumentException("Cannot transfer - from/to are same account " +  fromAccount.getId());
        }
        if (!accounts.contains(fromAccount)) {
            throw new IllegalArgumentException("Cannot transfer from account " +  fromAccount.getId() + " by another customer");
        }
        if (!accounts.contains(toAccount)) {
            throw new IllegalArgumentException("Cannot transfer to account " +  fromAccount.getId() + " by another customer");
        }
        fromAccount.withdraw(amount, transactionDate);
        toAccount.deposit(amount, transactionDate);
    }

    @Override
    public String getStatement() {
        return statementCreator.create(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountImpl)) {
            return false;
        }

        CustomerImpl customer = (CustomerImpl) o;

        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
