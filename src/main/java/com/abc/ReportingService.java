package com.abc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * A report service used for obtaining information on customers and accounts.
 */
public interface ReportingService {

    /**
     * Gets a list of all the bank customers, for reporting purposes.
     * @return report as a string
     */
    public List<Customer> getCustomers();

    /**
     * Gets a total of all interest paid to date.
     * @return amount as double
     */
    BigDecimal getTotalInterestPaid(Date toDate);
}
