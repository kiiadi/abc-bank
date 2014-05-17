/**
 * 
 */
package com.abc;

/**
 * @author Jeff
 * 
 */
public class SavingsAccount extends Account
	{

		/**
		 * Create a new savings account
		 * 
		 * @param _type
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
				double amount = sumTransactions();
				if (amount <= 1000)
					return amount * 0.001;
				else
					return 1 + (amount - 1000) * 0.002;
			}
	}
