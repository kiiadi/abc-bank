package com.abc.impl;

import com.abc.model.api.AccountManager;
import com.abc.model.api.CustomerManager;
import com.abc.model.api.ReportGenerator;
import com.abc.model.entity.*;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultReportGenerator implements ReportGenerator {

    private CustomerManager customerManager;
    private AccountManager accountManager;

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public CustomerReport createCustomerReport(Customer customer) {

        CustomerReport customerReport = new CustomerReport("Customer Statement", customer);

        for(Account account : customer.getAccounts()) {
            customerReport.addAccountToReport(account);
        }

        return customerReport;
    }

    @Override
    public CustomersAccountsReport createCustomersAccountsReport() {
        CustomersAccountsReport customersAccountsReport = new CustomersAccountsReport("Customer Summary");

        for(Customer customer : customerManager.getAllCustomers()) {
            customersAccountsReport.addCustomerToReport(customer);
        }

        return customersAccountsReport;
    }

    @Override
    public InterestAmountPaidReport createInterestAmountReport() {
        InterestAmountPaidReport interestAmountPaidReport = new InterestAmountPaidReport("Total Interest Amount Paid");

        for(Account account : accountManager.getAllAccounts()) {
            interestAmountPaidReport.addAccount(account);
        }

        return interestAmountPaidReport;
    }
}
