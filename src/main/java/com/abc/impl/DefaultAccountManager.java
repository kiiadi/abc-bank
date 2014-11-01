package com.abc.impl;

import com.abc.model.api.AccountManager;
import com.abc.model.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultAccountManager implements AccountManager {

    private List<Account> allAccounts = new ArrayList<Account>();

    @Override
    public Account openCheckingAccount(Customer customer, String accountName) {
        Account account = new CheckingAccount(accountName);
        return persistAccount(account,customer);
    }

    @Override
    public Account openSavingsAccount(Customer customer, String accountName) {
        Account account = new SavingsAccount(accountName);
        return persistAccount(account,customer);
    }

    @Override
    public Account openMaxiSavingsAccount(Customer customer, String accountName) {
        Account account = new MaxiSavingsAccount(accountName);
        return persistAccount(account,customer);
    }

    @Override
    public Transaction depositMoneyToAccount(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction(amount, Transaction.Type.CREDIT, new Date());
        account.getTransactions().add(transaction);
        return transaction;
    }

    @Override
    public Transaction withdrawMoneyFromAccount(Account account, BigDecimal amount) {
        if(account.isThereEnoughMoneyToDebit(amount)) {
            Transaction transaction = new Transaction(amount, Transaction.Type.DEBIT, new Date());
            account.getTransactions().add(transaction);
            return transaction;
        } else {
            throw new AttemptedAccountOverflow();
        }
    }

    @Override
    public void transferMoney(Transfer transfer) {
        Account sourceAccount = transfer.getSourceAccount();
        Account destinationAccount = transfer.getDestinationAccount();
        validateTransfer(transfer.getAmount());

        withdrawMoneyFromAccount(sourceAccount,transfer.getAmount());
        depositMoneyToAccount(destinationAccount,transfer.getAmount());
    }

    @Override
    public Transaction addInterest(Account account) {
        BigDecimal interestAmount = account.calculateInterest();
        Transaction transaction = new Transaction(interestAmount, Transaction.Type.INTEREST, new Date());
        account.getTransactions().add(transaction);
        return transaction;
    }

    @Override
    public List<Account> getAllAccounts() {
        return allAccounts;
    }

    private Account persistAccount(Account account, Customer customer) {
        allAccounts.add(account);
        customer.getAccounts().add(account);
        return account;
    }

    private void validateTransfer(BigDecimal amount) {
        if(amount.signum() == -1) {
            throw new AttemptedNegativeAmountTransfer();
        }
    }

}
