/**
 * 
 */
package com.abc;


/**
 * @author Jeff
 *
 */
public class InterestTransaction extends Transaction
	{
		/**
		 * Create a new Transaction object
		 * 
		 * @param _amount the amount of this transaction
		 */
		public InterestTransaction( double _amount)
			{
				super( _amount, TransactionType.DEPOSIT);
			}
	}
