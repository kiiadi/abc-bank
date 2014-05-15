/**
 * 
 */
package com.abc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class to encapsulate managing the transaction list for a particular account. 
 * This class uses a proxy pattern to hide the actual list and add additional 
 * functionalities for managing lists of transactions.
 * 
 * @author Jeff
 * 
 */
public class TransactionList implements Iterable<Transaction>
	{

		Account				account;
		List<Transaction>	list;

		/**
		 * privatize the default constructor so that derived classes 
		 * can not implement/call.
		 */
		private TransactionList()
			{
				this.list = new ArrayList<Transaction>();
			}

		/**
		 * Create a new transaction list
		 * @param _acct - the account that this list represents.
		 */
		public TransactionList(Account _acct)
			{
				this();
				account = _acct;
			}

		/**
		 * internal iterator for the list.
		 * 
		 * @author Jeff
		 *
		 */
		class __iterator__ implements Iterator<Transaction>
			{

				int	current	= 0;
				int	last	= list.size();

				/*
				 * Iterator::hasNext() method implementation
				 * 
				 * @see java.util.Iterator#hasNext()
				 */
				@Override
				public boolean hasNext()
					{
						return (current < last ? true : false);
					}

				/**
				 * Iterator::next method implementation
				 * 
				 * @see java.util.Iterator#next()
				 */
				@Override
				public Transaction next()
					{
						return list.get(current++);
					}

				/**
				 * Iterator::remove() method implementation
				 * 
				 * @see java.util.Iterator#remove()
				 */
				@Override
				public void remove()
					{
						list.remove(current);

						// always return the prior element or zero if it was the
						// first
						current = Math.max(current - 1, 0);
						// reset the last element index
						last = list.size() - 1;
					}
			}

		/**
		 * Create an iterator for the transaction list
		 * 
		 * @see java.lang.Iterable#iterator()
		 */
		@Override
		public Iterator<Transaction> iterator()
			{
				return new __iterator__();
			}

		/**
		 * Add a new transaction to the list
		 * @param _t - a transaction to add
		 */
		public void add(Transaction _t )
			{
				if( !isValid(_t) )
					throw new IllegalArgumentException( "The transaction is not valid.");
				list.add(_t);
			}
		
		/**
		 * Validate a transaction. This is called before trying to add a transaction
		 * to the list. This is defined as public so that transactions can be validated
		 * prior to trying to add them to the list.
		 * 
		 * @param _t - the transaction
		 * @return true - if the transaction could be added to the list or false if 
		 * it cannot
		 */
		public boolean isValid(Transaction _t )
			{
				return true;
			}		
	}
