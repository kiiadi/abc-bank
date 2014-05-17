/**
 * 
 */
package com.abc;

import java.util.Date;

/**
 * An abstract class representing an account transaction.
 * 
 * @author Jeff
 * 
 */
public abstract class Transaction
	{

		protected double				amount = 0.00;
		protected TransactionType		transType = TransactionType.UNKNOWN;
		protected Date					transactionDate = null;


		/*
		 * Made private so that no one who derives can create an empty transaction. 
		 * Takes care of need to make 'final' for instance variables.
		 */
		private Transaction() 
			{
				
			}
		
		protected Transaction(Account _acct, double _amount, TransactionType _type) 
		{
			this();
			this.amount = _amount;
			this.transType = _type;
			this.transactionDate = Utils.now();
		}

		public abstract void commit() throws Exception;
	}