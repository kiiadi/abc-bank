/**
 * 
 */
package com.abc;

import java.util.Properties;

/**
 * A class representing a customer's maxi-savings account (<i>Account</i> subclass)
 * 
 * @author Jeff
 * 
 */
public class MaxiSavingsAccount extends Account
	{

		/**
		 * Create a new maxi savings account
		 */
		public MaxiSavingsAccount()
			{
				super(AccountType.MAXISAVINGS);
			}

		/**
		 * Calculate interest on a maxi-savings account
		 * 
		 * return the interest on the current balance for the current period.
		 * 
		 * @see com.abc.Account#calculateInterest()
		 */
		@Override
		public double calculateInterest()
			{
				double amount = sumTransactions();
				
				if (amount <= 1000)
					return amount * 0.02;
				if (amount <= 2000)
					return 20 + (amount - 1000) * 0.05;
				return 70 + (amount - 2000) * 0.1;
			}

		/**
		 * Reset to default maxi-account settings
		 * 
		 * @see com.abc.Account#resetProperties()
		 */
		@Override
		protected void resetProperties(Properties _p )
			{
				// clear all the current property entries
				_p.clear();
				
				// set the base rate
				_p.setProperty("base_rate", "0.001");				
			}

		/**
		 * Return the name of the properties file for Account::loadProperties.
		 * 
		 * @see com.abc.Account#getPropertiesFilename()
		 */
		@Override
		protected String getPropertiesFilename()
			{
				return "maxisavings.properties";
			}



	}
