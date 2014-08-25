package com.abc.impl;

import com.abc.api.*;
import com.abc.services.AccountIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankImpl implements Bank {

    private static final Logger logger = LoggerFactory.getLogger(BankImpl.class);

    private Map<Customer, Customer> customers = new HashMap<>();
    private Map<Customer, Map<AccountType, List<Account>>> customerAccounts = new HashMap<>();
    private Map<AccountType, InterestCalculator> interestCalculatorMap = new HashMap<>();

    private AccountIdGenerator accountIdGenerator;

    public void setAccountIdGenerator(AccountIdGenerator accountIdGenerator) {
        this.accountIdGenerator = accountIdGenerator;
    }

    public void addAccountType(AccountType accountType, InterestCalculator interestCalculator) {
        interestCalculatorMap.put(accountType, interestCalculator);
    }


    @Override
    public CustomerId addCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean isValidCustomer(CustomerId customerId) {
        return false;
    }

    @Override
    public AccountId addAccount(Customer customer, AccountType accountType) {
        return null;
    }

    @Override
    public boolean isValidAccount(Customer customer, AccountId accountId) {
        return false;
    }

    @Override
    public TransactionId addTransaction(Customer customer, AccountId accountId, Transaction transaction) {
        return null;
    }

    @Override
    public TransactionId addTransfter(Customer customer, AccountId source, Account target, Transfer transfer) {
        return null;
    }

    @Override
    public CustomerStatement getCustomerStatement(Customer customer) {
        return null;
    }

    @Override
    public ManagerReport getManagerReport(ReportType reportType) {
        return null;
    }
}
