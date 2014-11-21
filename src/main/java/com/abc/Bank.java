package com.abc;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
	
	private final static double EPSLON = 1e-15;

	private Map<String, Customer> customers;

    public Bank() {
        customers = new LinkedHashMap<String, Customer>();
    }

    public void addCustomer(Customer customer) {
    	//Modified by Henry chen
    	if(customer == null) return;
        customers.put(customer.getName(), customer);
    }
    
    public String customerSummary() {
    	StringBuilder summary = new StringBuilder("Customer Summary");
    	for (Customer c : customers.values())
            summary.append("\n - ").append(c.getName()).append(" (").append(format(c.getNumberOfAccounts(), "account")).append(")");
        return summary.toString();
    }
    
    public void transfer (String name, double ammount, String fromAccountNumber,  String toAccountNumber) {
    	if(!customers.containsKey(name))
    		throw new IllegalArgumentException(name + " does not have an account with us.");
    	
    	Customer customer = customers.get(name);
    	Account fromAccount = customer.getAccount(fromAccountNumber);
    	Account toAccount = customer.getAccount(toAccountNumber);
    	
    	fromAccount.withdraw(ammount);
        toAccount.deposit(ammount);
		
    }
   

    // building String should use StringBuilder. Commented out by Henry Chen
   /* public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }*/
    
    

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers.values())
            total += c.totalInterestEarned();
        return total;
    }
    
    public String getFirstCustomer() {
    	
       return (customers.isEmpty())?null:customers.values().iterator().next().getName();
        
    }

    // This method has tow bugs and commented out by Henry Chen
    // 1. customer is always null
    // 2. catch clause will not cacth null point exception 
   /* public String getFirstCustomer() {
    	
    	
        try {
            customers = null;
            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }*/
}
