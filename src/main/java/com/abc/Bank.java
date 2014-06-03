package com.abc;

import java.util.Map;   
import java.util.TreeMap;
import java.util.Collections;

/**
 * The Class Bank.
 */
public class Bank {
	
	private static final String CUSTOMER_SUMMARY_TEXT="Customer Summary";
	private static final String ONE_ACCOUNT_TEXT="account";
	private static final String ACCOUNTS_TEXT="accounts";
	
	
	/** The bank name */
	private String bankName = null;
	
    /** The customers. */
    private Map<String, Customer> customers = null;
    

    /**
     * Instantiates a new bank.   For Customers use a TreeMap for the natural ordering of the keys, 
     * so our summaries are alphabetized
     * 
     * @param nm the name of the bank
     */
    public Bank(String nm) {
    	bankName = nm;
        customers = (Map<String, Customer>)Collections.synchronizedMap(new TreeMap<String, Customer>());
    }
    
    
    /**
     * 
     * @return the bankName
     */
    public String getBankName() {
    	return bankName;
    }


    /**
     * Adds the customer.  
     *
     * @param customer the customer
     * @return 0 on success, else failure
     */
    public int addCustomer(Customer c) {
        if (c != null) {        	
            String customerIdStr = String.valueOf(c.getCustomerId());
            customers.put(customerIdStr, c);
            return 0;
        }
        else {
          	return -1;
        }        
    }

    
    /**
      * Format the account text according to the number of accounts for the Customer Summary.
      *
      * @param number the number of accounts
      * @return the appropriate spelling of the account text string
      */
     private String getCustomerSummaryAccountText(int number) {
         return (number == 1) ? ONE_ACCOUNT_TEXT : ACCOUNTS_TEXT;
     }

          
    /**
     * Customer summary.    
     *
     * @return the string describing the customer summary of how many accounts they have.     
     */
    public String customerSummary() {
        StringBuffer summaryBuff = new StringBuffer();
        
        //Initialize with a header and then populate with customer account info
        summaryBuff.append(CUSTOMER_SUMMARY_TEXT);
        
        for (Map.Entry<String, Customer> entry : customers.entrySet()) {
    	    Customer c = entry.getValue();
                
            summaryBuff.append("\n - ");
            summaryBuff.append("customerName: ");
            summaryBuff.append(c.getCustomerName());
            summaryBuff.append(" : ");
            summaryBuff.append(c.getCustomerId());
            summaryBuff.append(" (");
            summaryBuff.append(c.getNumberOfAccounts());
            summaryBuff.append(" ");
            summaryBuff.append(getCustomerSummaryAccountText(c.getNumberOfAccounts())); 
            summaryBuff.append(")\n");
            summaryBuff.append("Total Interest earned: ");
            summaryBuff.append(c.totalCompoundInterestEarned());
        }
        
        return summaryBuff.toString();
    }

   
    /**
     * Total interest paid for all customers.    
     *
     * @return the total interest paid for all customers, as a double
     */
    public double totalCompoundInterestPaid() {
        double totalCompoundInterest = 0;
        
        for (Map.Entry<String, Customer> entry : customers.entrySet()) {
    	    Customer c = entry.getValue();
            totalCompoundInterest += c.totalCompoundInterestEarned();
        }
        
        return totalCompoundInterest;
    }
    
}
