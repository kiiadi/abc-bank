package com.abc;

/**
 * A class representing a deposit transaction class
 * 
 * @author Jeff
 * 
 */
public class DepositTransaction extends Transaction
	{

		/**
		 * Create a new Transaction object
		 * 
		 * @param amount - the amount of this transaction
		 */
		public DepositTransaction(Account _acct, double _amount)
			{
				super(_acct, _amount, TransactionType.DEPOSIT);
			}

		/**
		 * Commit the the deposit
		 * 
		 * @see com.abc.Transaction#commit()
		 */
		@Override
		public void commit() throws CommitException
			{
				// TODO Auto-generated method stub
			}

	}
