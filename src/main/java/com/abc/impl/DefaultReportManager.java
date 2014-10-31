package com.abc.impl;

import com.abc.model.api.CustomerManager;
import com.abc.model.api.ReportManager;
import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.CustomerReport;
import com.abc.model.entity.CustomersAccountsReport;

import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultReportManager implements ReportManager {

    private CustomerManager customerManager;

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
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
}
