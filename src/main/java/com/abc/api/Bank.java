package com.abc.api;

public interface Bank {
    CustomerId addCustomer(Customer customer);
    boolean isValidCustomer(CustomerId customerId);

    AccountId addAccount(Customer customer, AccountType accountType);
    boolean isValidAccount(Customer customer, AccountId accountId);

    TransactionId addTransaction(Customer customer, AccountId accountId, Transaction transaction);
    TransactionId addTransfter(Customer customer, AccountId source, Account target, Transfer transfer);

    CustomerStatement getCustomerStatement(Customer customer);
    ManagerReport getManagerReport(ReportType reportType);




}
