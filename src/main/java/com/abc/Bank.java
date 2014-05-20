package com.abc;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abc.interfaces.BankDetail;
import com.abc.interfaces.CustomerDetail;

public class Bank implements BankDetail{
	private String m_code;
    private Map<String, Customer> m_customers;

    public Bank() {
        m_customers = new LinkedHashMap<String, Customer>();
    }
    
    public Map<String, Customer> getCustomers(){
    	return m_customers;
    }

    public CustomerDetail createNewCustomer(String accountName){
		Customer customer = new Customer(accountName);
		getCustomers().put(accountName, customer);
		return customer;
	}
    
    public void addCustomer(Customer customer) {
        m_customers.put(customer.getName(), customer);
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : m_customers.values())
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: m_customers.values())
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        try {
            m_customers = null;
            return m_customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
	 
	public String getCode() {
		return m_code;
	}

	public void setCode(String code) {
		m_code = code;
	}
}
