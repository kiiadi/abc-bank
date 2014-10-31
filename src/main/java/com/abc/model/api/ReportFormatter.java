package com.abc.model.api;

import com.abc.model.entity.CustomerReport;
import com.abc.model.entity.CustomersAccountsReport;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public interface ReportFormatter {

    String formatCustomerReport(CustomerReport customerReport);
    String formatCustomersAccountsReport(CustomersAccountsReport customersAccountsReport);

}
