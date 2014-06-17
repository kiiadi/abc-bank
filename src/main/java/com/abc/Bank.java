package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to create Bank
 */
public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    /**
     * Adds a new customer to bank based on name and Account Type
     * Note: An account is needed to be registered customer for a bank
     * @param name
     * @param accountType
     * @return
     */
    public Customer addNewCustomer(String name, int accountType) {
        Customer customer = new Customer(name, accountType);
        customers.add(customer);
        return customer;
    }

    /**
     * Presents a bank level report for all the customers and the number of accounts held by them
     * @return
     */
    public String customerSummary() {
        StringBuffer summary = new StringBuffer("Customer Summary");
        for (Customer c : customers)
            summary.append("\n - " + c.getName() + " (" +
                    pluralFormat(c.getNumberOfAccounts(), "account") + ")");
        return summary.toString();
    }

    /**
     * Make sure correct plural of word is created based on the number passed in:
     * If number passed in is 1 just return the word otherwise add an 's' at the end
     * @return
     */
    private String pluralFormat(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    /**
     * Calculates the total interest paid to all customers
     * @return
     */
    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    /**
     * Returns the first customer of the bank
     * @return
     */
    public String getFirstCustomer() {
        if(customers.size() >0){
            return customers.get(0).getName();
        }
        return "No Customers";
    }
}
