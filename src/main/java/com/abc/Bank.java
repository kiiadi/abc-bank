package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {
    private HashMap<String, Customer> customers;

    public Bank() {
        customers = new HashMap<String, Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getName(),customer);
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (String customerName : customers.keySet())
            summary += "\n - " + customers.get(customerName).getName() + " (" + format(customers.get(customerName).getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(String customerName: customers.keySet())
            total += customers.get(customerName).totalInterestEarned();
        return total;
    }

//    public String getFirstCustomer() {
//        try {
//            customers = null;
//            return customers.get(0).getName();
//        } catch (Exception e){
//            e.printStackTrace();
//            return "Error";
//        }
//    }
}
