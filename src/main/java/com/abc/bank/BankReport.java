/**
 * 
 */
package com.abc.bank;

import java.util.Collection;

import com.abc.customer.Customer;

/**
 * Contains static helper methods used for Bank reports
 *
 */
public class BankReport {

	public static String customerSummary(Collection<Customer> customers) {
        StringBuilder summary = new StringBuilder("Customer Summary");
        
        for (Customer customer : customers) {
            summary.append("\n - ")
                   .append(customer.getName())
                   .append(":")
                   .append(customer.getCustomerId())
                   .append(" (")
                   .append(format(customer.getNumberOfAccounts(), "account"))
                   .append(")");
        }
        
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private static String format(int number, String word) {
    	StringBuilder strNumber = new StringBuilder();
    	strNumber.append(number)
    	         .append(" ")
    	         .append(number == 1 ? word : word + "s");
    	
        return strNumber.toString();
    }
}
