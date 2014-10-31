package com.abc.model.api;

import com.abc.model.entity.CustomerReport;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public interface ReportFormatter {

    String formatCustomerReportToBasicFormat(CustomerReport customerReport);

}
