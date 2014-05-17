/**
 * 
 */
package com.abc;

import java.util.Properties;

/**
 * A class representing a customers savings account (<i>Account</i> subclass)
 * @author Jeff
 * 
 */
public class SavingsAccount extends Account
	{

		/**
		 * Create a new savings account
		 */
		public SavingsAccount()
			{
				super(AccountType.SAVINGS);
			}

		/**
		 * Calculate the interest on the Savings Account
		 * 
		 * @return the interest for the current balance in the current period
		 * 
		 * @see com.abc.Account#calculateInterest()
		 */
		@Override
		public double calculateInterest()
			{
				String base_rate_str = this.getProperty("base_rate");
				String ext_rate_str = this.getProperty("ext_rate");
				
				double amount = sumTransactions();
				double rate = Double.valueOf(base_rate_str);
				double ext_rate = Double.valueOf(ext_rate_str);
				
				double interest = 0.00;
				
				if (amount > 1000)
					{
					interest = 1000 * rate + (amount - 1000 ) * ext_rate;
					}
				else
					interest = amount * rate;
				
				return interest;
			}

		/** 
		 * Reset to default savings account properties
		 * 
		 * @see com.abc.Account#resetProperties()
		 */
		@Override
		protected void resetProperties(Properties _p)
			{
				// clear all the current property entries
				_p.clear();
				
				// set the base rate
				_p.setProperty("base_rate", "0.001");
				_p.setProperty("ext_rate", "0.002");
				
			}
		
		/**
		 * Return the name of the properties file for Account::loadProperties.
		 * 
		 * @see com.abc.Account#getPropertiesFilename()
		 */
		@Override
		protected String getPropertiesFilename()
			{
				return "savings.properties";
			}


	}
