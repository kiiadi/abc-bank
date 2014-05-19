/**
 * 
 */
package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * A class representing a customer's maxi-savings account (<i>Account</i>
 * subclass)
 * 
 * @author Jeff
 * 
 */
public class MaxiSavingsAccount extends Account
	{

		private Date	last_withdraw_dt	= null;

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
				String base_rate_str = this.getProperty("base_rate");
				String ext_rate_str = this.getProperty("ext_rate");

				// convert strings to actual rates
				double base_rate = Double.valueOf(base_rate_str);
				double ext_rate = Double.valueOf(ext_rate_str);
				double rate = 0.00;

				double interest = 0.00;

				// get the current balance
				double balance = getBalance(true);

				Date last_withdrawal = this.getLastWithdrawDate();

				// if there were no withdrawals
				if (last_withdrawal == null)
					rate = ext_rate; // use the extended rate
				else
					{
						Calendar last = new GregorianCalendar();
						Calendar today = new GregorianCalendar();
					
						last.setTime(last_withdrawal);
						today.setTime(Utils.now());

						if (today.get(Calendar.DAY_OF_YEAR) - last.get(Calendar.DAY_OF_YEAR) >= 10)

							rate = ext_rate;
						else
							rate = base_rate;
					}
				interest = balance * rate;
				// return the interest
				return interest;
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
		protected void resetProperties(Properties _p)
			{
				// clear all the current property entries
				_p.clear();

				// set the base rate
				_p.setProperty("base_rate", "0.02");
				// set the extended rate
				_p.setProperty("ext_rate", "0.05");
				// set the base rate
				_p.setProperty("enh_rate", "0.1");

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

		/**
		 * Gets the date of the last withdrawal
		 * 
		 * @return the last_withdraw_dt
		 */
		public Date getLastWithdrawDate()
			{
				return last_withdraw_dt;
			}
	}
