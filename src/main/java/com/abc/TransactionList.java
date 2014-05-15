/**
 * 
 */
package com.abc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jeff
 * 
 */
public class TransactionList implements Iterable<Transaction>
	{

		Account				account;
		List<Transaction>	list;

		private TransactionList()
			{
				this.list = new ArrayList<Transaction>();
			}

		public TransactionList(Account _acct)
			{
				this();
				account = _acct;
			}

		class __iterator__ implements Iterator<Transaction>
			{

				int	current	= 0;
				int	last	= list.size();

				/*
				 * (non-Javadoc)
				 * @see java.util.Iterator#hasNext()
				 */
				@Override
				public boolean hasNext()
					{
						return (current < last ? true : false);
					}

				/*
				 * (non-Javadoc)
				 * @see java.util.Iterator#next()
				 */
				@Override
				public Transaction next()
					{
						return list.get(current++);
					}

				/*
				 * (non-Javadoc)
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
		public void add(Transaction _t)
			{
				if( !isValid(_t) )
					throw new IllegalArgumentException( "The transaction is not valid.");
				list.add(_t);
			}
		
		/**
		 * Test to see that the transaction is valid before adding to the list
		 * @param _t - the transaction
		 * @return true - if the transaction could be added to the list or false if 
		 * it cannot
		 */
		public boolean isValid(Transaction _t )
			{
				return true;
			}
	}
