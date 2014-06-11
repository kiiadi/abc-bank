package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A bank, that offers reporting, admin and customer facing services.
 */
public class Bank implements CustomerService, ReportingService, AdminService {

    private final Map<String, CustomerImpl> customers = new HashMap<>();

    @Override
    public Customer getCustomer(final String id) {
        return customers.get(id);
    }

    public void addCustomer(CustomerImpl customer) {
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer already registered with id " + customer.getId());
        }
        customers.put(customer.getId(), customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public BigDecimal getTotalInterestPaid(Date toDate) {
        BigDecimal total = BigDecimal.ZERO;
        for (CustomerImpl customer: customers.values()) {
            total = total.add(customer.getTotalInterestEarned(toDate));
        }
        return total;
    }

}
