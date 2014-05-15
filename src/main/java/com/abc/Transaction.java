package com.abc;

import java.util.Date;

/**
 * The Transaction class
 * 
 * @author Jeff
 * 
 */
public class Transaction
	{

		public final double				amount;
		public final TransactionType	transType;

		private Date					transactionDate;

		/**
		 * Create a new Transaction object
		 * 
		 * @param amount - the amount of this transaction
		 */
		public Transaction(double _amount, TransactionType _type)
			{
				this.amount = _amount;
				this.transType = _type;
				this.transactionDate = Utils.now();
			}

		
	}
