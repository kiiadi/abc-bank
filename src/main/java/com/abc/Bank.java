package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * The Bank class
 * 
 * @author Jeff
 * 
 */
public class Bank
	{

		private List<Customer>	customers;

		/**
		 * Create a new bank object
		 */
		public Bank()
			{
				customers = new ArrayList<Customer>();
			}

		/**
		 * Add a new customer to the bank
		 * 
		 * @param customer the customer to add
		 */
		public void addCustomer(Customer customer)
			{
				customers.add(customer);
			}

		/**
		 * Run the customer summary report
		 * 
		 * @return a string representing the customer summary report.
		 */
		public String customerSummary()
			{
				String summary = "Customer Summary";
				for (Customer c : customers)
					summary += "\n - "
									+ c.getName()
									+ " ("
									+ makePlural(c.getNumberOfAccounts(), "account")
									+ ")";
				return summary;
			}

		/**
		 * Make sure correct plural of word is created based on the number
		 * passed in.
		 * 
		 * @param number - the number to analyze
		 * @param word - the word to add a plural to..oy!
		 * @returns - a string representing the number with an 's' at the end if
		 *          	it is greater than zero.
		 */
		private String makePlural(int number, String word)
			{
				// If the number passed in is 1 just return the word otherwise
				// add an 's' at the end
				return number + " " + (number == 1 ? word : word + "s");
			}

		/**
		 * calculate the total interest paid
		 * 
		 * @return the interest paid.
		 */
		public double totalInterestPaid()
			{
				double total = 0;
				for (Customer c : customers)
					total += c.totalInterestEarned();
				
				return total;
			}

	}
