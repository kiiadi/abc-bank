package com.abc;

/**
 * A class representing a customer deposit type of transaction.
 * 
 * @author Jeff
 * 
 */
public class DepositTransaction extends Transaction
	{

		/**
		 * Create a new Transaction object
		 * 
		 * @param _acct the account that this transaction refers to
		 * @param _amount the amount of this transaction
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
		public void commit() throws AccountOperationException
			{
				// TODO Auto-generated method stub
			}

	}
