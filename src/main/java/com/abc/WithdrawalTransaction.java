package com.abc;

/**
 * The Transaction class
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
				super(_acct, _amount, TransactionType.WITHDRAWAL);
			}

		/**
		 * Commit the withdrawal
		 * @see com.abc.Transaction#commit()
		 */
		@Override
		public void commit() throws CommitException
			{
				// TODO Auto-generated method stub
			}

	}
