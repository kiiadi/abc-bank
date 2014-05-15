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
									+ Utils.makePlural(c.getNumberOfAccounts(),
													"account") + ")";
				return summary;
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
