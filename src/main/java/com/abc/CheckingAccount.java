/**
 * 
 */
package com.abc;

import java.util.Properties;



/**
 * A class representing a checking account (<i>Account</i> subclass).
 * @author Jeff
 * 
 */
public class CheckingAccount extends Account
	{

		/**
		 * Create a new checking account
		 * 
		 */
		public CheckingAccount()
			{
				super(AccountType.CHECKING);
			}

		/**
		 * Checking account interest calculation.
		 * 
		 * Return the interest on the checking account
		 * 
		 * @see com.abc.Account#calculateInterest()
		 */
		@Override
		public double calculateInterest()
			{
				String rate_str = this.getProperty("base_rate");
				
				double amount = sumTransactions();
				double rate = Double.valueOf(rate_str);
				
				return amount * rate;
			}


		/**
		 * Reset the checking account properties to default values.
		 * 
		 */
		protected void resetProperties( Properties _p)
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
				return "checking.properties";
			}



	}
