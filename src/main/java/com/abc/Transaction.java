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
		
		/**
		 * @return the transactionDate
		 */
		public Date getTransactionDate()
			{
				return transactionDate;
			}

		
		/**
		 * @param transactionDate the transactionDate to set
		 */
		public void setTransactionDate(Date transactionDate)
			{
				this.transactionDate = transactionDate;
			}

		
		/**
		 * @return the amount
		 */
		public double getAmount()
			{
				return amount;
			}

		
		/**
		 * @return the transType
		 */
		public TransactionType getTransType()
			{
				return transType;
			}

		protected Date					transactionDate = null;


		/*
		 * Made private so that no one who derives can create an empty transaction. 
		 * Takes care of need to make 'final' for instance variables.
		 */
		private Transaction() 
			{
				
			}
		
		protected Transaction( double _amount, TransactionType _type) 
		{
			this();
			this.amount = _amount;
			this.transType = _type;
			this.transactionDate = Utils.now();
		}

	}