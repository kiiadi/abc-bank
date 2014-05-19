/**
 * 
 */
package com.abc;


/**
 * A class representing an account transfer going to another account. 
 * 
 * @author Jeff
 *
 */
public class TransferOutTransaction extends Transaction
	{
		protected Account ref_account;	

		
		/**
		 * Get the account that this transfer came in from.
		 * 
		 * @return the reference account.
		 */
		public Account getRefAccount()
			{
				return ref_account;
			}

	/**
	 * Create a new TransferInTransaction object
	 * @param _amount the amount of the transaction
	 * @param _type the type of the transaction
	 */
	protected TransferOutTransaction(Account _acct, double _amount)
		{
			super(_amount, TransactionType.TRANSFER_TO );
			this.ref_account = _acct;
		}

	}
