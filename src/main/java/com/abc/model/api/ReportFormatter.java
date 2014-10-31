package com.abc.model.api;

import com.abc.model.entity.CustomerReport;
import com.abc.model.entity.CustomersAccountsReport;
import com.abc.model.entity.InterestAmountPaidReport;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public interface ReportFormatter {

    String formatInterestAmountPaidReport(InterestAmountPaidReport interestAmountPaidReport);
    String formatCustomersAccountsReport(CustomersAccountsReport customersAccountsReport);
    String formatCustomerReport(CustomerReport customerReport);



}
