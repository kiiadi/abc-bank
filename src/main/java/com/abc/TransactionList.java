/**
 * 
 */
package com.abc;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * A class representing lists of transactions. A facade to the actual list.
 * 
 * @author Jeff
 * 
 */
public class TransactionList implements Iterable<Transaction>
	{

		private List<Transaction>	list;

		private Account				account;
		
		@SuppressWarnings("hiding")
		public class TransactionIterator<Transaction> implements Iterator<com.abc.Transaction>
			{

				private int current;
				private int last;

				public TransactionIterator()
					{
						current = 0;
						last = list.size() - 1;
					}

				public boolean hasNext()
					{
						return ( current < last ? true : false );
					}

				public com.abc.Transaction next()
					{
						return list.get(current++);
					}

				/**
				 * @see java.util.Iterator#remove()
				 */
				public void remove()
					{
						list.remove(current);
					}

			}

		public Iterator<com.abc.Transaction> iterator()
			{
				return new TransactionIterator<com.abc.Transaction>();
			}

		/**
		 * prevent unauthorized creation.
		 */
		private TransactionList()
			{
				
			}

		/**
		 * create a new transaction list
		 */
		public TransactionList(Account _acct)
			{
				this();
				this.account = _acct;
				this.list = new ArrayList<Transaction>();
			}

		/**
		 * Check to see if the transaction would be valid if committed on this
		 * account
		 * 
		 * @param _t - the transaction to evaluate
		 * @return - true if the transaction can be posted to the account or
		 *         false if it cannot
		 */
		public boolean isValidTransaction(Transaction _t)
			{
				return false; // transaction should not post to this account
			}

		/**
		 * Add a transaction to the list
		 */
		public void addTransaction()
			{

			}

	}
