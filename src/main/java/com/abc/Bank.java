package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alex Gordon
 * Bank class
 *
 */
public class Bank {

    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

	public void addCustomer(Customer customer) {
		if (this.customers.contains(customer)) {
			throw new IllegalArgumentException(Constants.DUPLICATE_CUSTOMER_ERROR);
		} else {
			customers.add(customer);
		}
	}

    public String customerSummaryReport() {
        String summary = "Customer Summary Report";
        for (Customer c : customers)
            summary += "\n - " + c.summary();
        return summary;
    }

    public String totalInterestPaidTodayReport() {
        String summary = "Total Interest Paid Today";
            summary += "\n - " + Constants.toDollars(totalInterestPaidToday());
        return summary;
    }

    private double totalInterestPaidToday() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarnedToday();
        return total;
    }

	public List<Customer> getCustomers() {
		return customers;
	}

}
