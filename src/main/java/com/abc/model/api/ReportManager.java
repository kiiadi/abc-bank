package com.abc.model.api;

import com.abc.model.entity.Customer;
import com.abc.model.entity.CustomerReport;
import com.abc.model.entity.CustomersAccountsReport;
import com.abc.model.entity.InterestAmountPaidReport;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public interface ReportManager {

    CustomerReport createCustomerReport(Customer customer);
    CustomersAccountsReport createCustomersAccountsReport();
    InterestAmountPaidReport createInterestAmountReport();

}
