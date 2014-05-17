/**
 * 
 */
package com.abc;

/**
 * A class representing a Maxi-Savings account
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

	}
