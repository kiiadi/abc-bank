/**
 * 
 */
package com.abc;

/**
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
				double amount = sumTransactions();

				return amount * 0.001;
			}

	}
