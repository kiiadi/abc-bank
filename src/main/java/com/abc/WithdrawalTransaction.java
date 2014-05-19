package com.abc;

/**
 * A class representing a customer withdrawal from the account.
 * 
 * @author Jeff
 * 
 */
public class WithdrawalTransaction extends Transaction
	{
		/**
		 * Create a new Transaction object
		 * 
		 * @param _acct the account to execute the transaction on
		 * @param _amount the amount of this transaction
		 */
		public WithdrawalTransaction(Account _acct, double _amount )
			{
				super(_amount, TransactionType.WITHDRAWAL);
			}
	}
