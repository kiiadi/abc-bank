package com.abc.impl.manager;

import com.abc.model.api.AccountManager;
import com.abc.model.entity.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultAccountManager implements AccountManager {

    @Override
    public Account openCheckingAccount(Customer customer) {
        Account account = new CheckingAccount();
        customer.getAccounts().add(account);
        return account;
    }

    @Override
    public Account openSavingsAccount(Customer customer) {
        Account account = new SavingsAccount();
        customer.getAccounts().add(account);
        return account;
    }

    @Override
    public Account openMaxiSavingsAccount(Customer customer) {
        Account account = new MaxiSavingsAccount();
        customer.getAccounts().add(account);
        return account;
    }

    @Override
    public Transaction depositMoneyToAccount(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction(amount, Transaction.Type.CREDIT, new Date());
        account.getTransactions().add(transaction);
        return transaction;
    }

    @Override
    public Transaction withdrawMoneyFromAccount(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction(amount, Transaction.Type.DEBIT, new Date());
        account.getTransactions().add(transaction);
        return transaction;
    }
}
