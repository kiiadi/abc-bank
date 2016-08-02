package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        String summary = BankConstant.CUSTOMARY_SUMMARY.type();
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), BankConstant.ACCOUNT.type()) + ")";
        return summary;
    }

    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() throws AbcBankException {
        try {
            if(customers != null)
                return customers.get(0).getName();
        } catch (Exception e){
            throw new AbcBankException(e.getMessage(),BankConstant.FIRST_CUSTOMER_EXCEPTION.type());
        }
        return BankConstant.NO_CUSTOMER.type();
    }
}
