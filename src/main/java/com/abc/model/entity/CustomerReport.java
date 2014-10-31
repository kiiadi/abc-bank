package com.abc.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr koller on 31/10/2014.
 */

//This in the requirements is referred to as customer 'statement'
public class CustomerReport extends Report {

    private String customerName;
    private List<CustomerReportItem> reportItems = new ArrayList<CustomerReportItem>();

    public CustomerReport(String name, Customer customer) {
        super(name);
        this.customerName = customer.getName();
    }

    public List<CustomerReportItem> getReportItems() {
        return reportItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void addAccountToReport(Account account) {
        reportItems.add(new CustomerReportItem(account));
    }


    public static class CustomerReportItem {
        private Account account;

        public CustomerReportItem(Account account) {
            this.account = account;
        }

        public Account getAccount() {
            return account;
        }
    }
}
