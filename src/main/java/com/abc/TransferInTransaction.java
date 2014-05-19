/**
 * 
 */
package com.abc;


/**
 * A class that represents an account transfer coming in from another account.
 * 
 * @author Jeff
 *
 */
public class TransferInTransaction extends Transaction
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
	protected TransferInTransaction(Account _acct, double _amount)
		{
			super(_amount, TransactionType.TRANSFER_FROM);
			this.ref_account = _acct;
		}
	}
