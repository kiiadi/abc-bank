/**
 * 
 */
package com.abc;

import java.util.Date;
import java.util.Properties;

/**
 * A class representing a customer's maxi-savings account (<i>Account</i> subclass)
 * 
 * @author Jeff
 * 
 */
public class MaxiSavingsAccount extends Account
	{

		private Date last_withdraw_dt = null;
		
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
				double amount = getBalance(true);
				
				if (amount <= 1000)
					return amount * 0.02;
				if (amount <= 2000)
					return 20 + (amount - 1000) * 0.05;
				return 70 + (amount - 2000) * 0.1;
			}

		/**
		 * Withdraw an amount from the account
		 * 
		 * @see com.abc.Account#withdraw(double)
		 */
		@Override
		public Transaction withdraw(double _amount)
			{
				// make sure we call the super classes <i>withdraw</i> method
				Transaction t = super.withdraw(_amount);
				
				// update the last withdrawal date so we don't need to look it up
				this.last_withdraw_dt = t.getTransactionDate();
				
				// return the transaction 
				return t;
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
