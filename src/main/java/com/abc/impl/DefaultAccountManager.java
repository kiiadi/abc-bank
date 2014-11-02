package com.abc.impl;

import com.abc.model.api.AccountManager;
import com.abc.model.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultAccountManager implements AccountManager {

    private List<Account> allAccounts = Collections.synchronizedList(new ArrayList<Account>());

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
    public void deleteAllAccounts() {
        allAccounts.clear();
    }

    @Override
    public void depositMoneyToAccount(Account account, BigDecimal amount) {
        account.creditAccount(amount);
    }

    @Override
    public void withdrawMoneyFromAccount(Account account, BigDecimal amount) {
        if(account.isThereEnoughMoneyToDebit(amount)) {
            account.debitAccount(amount);
        } else {
            throw new AttemptedAccountOverdraft();
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
    public void addInterest(Account account) {
        BigDecimal interestAmount = account.calculateInterest();
        account.addInterest(interestAmount);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<Account>(allAccounts);
    }

    private Account persistAccount(Account account, Customer customer) {
        allAccounts.add(account);
        customer.addAccount(account);
        return account;
    }

    private void validateTransfer(BigDecimal amount) {
        if(amount.signum() == -1) {
            throw new AttemptedNegativeAmountTransfer();
        }
    }

}
