package com.abc.impl.manager;

import com.abc.model.api.ReportManager;
import com.abc.model.entity.Account;
import com.abc.model.entity.Customer;
import com.abc.model.entity.CustomerReport;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultReportManager implements ReportManager {

    @Override
    public CustomerReport createCustomerReport(Customer customer) {

        CustomerReport customerReport = new CustomerReport("Customer Statement", customer);

        for(Account account : customer.getAccounts()) {
            customerReport.addAccountToReport(account);
        }

        return customerReport;
    }


}
