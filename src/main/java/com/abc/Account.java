package com.abc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * An abstract class representing a customers bank account.
 * 
 * @author Jeff
 * 
 */

public abstract class Account
	{

		private AccountType		accountType;
		private TransactionList	transactions;
		private double			balance;
		private DataState		balance_state;
		private Properties		props;
		private Date			first_transaction_dt;		// date of first transaction for calucalting 
															// interest
		private Date			last_interest_dt;			// the last time that the interest was posted to
															// this account

		/**
		 * Make sure that sub-classing cannot use 'default' ctor and it is only
		 * used internally.
		 */
		private Account()
			{
				this.balance_state = DataState.CLEAN;

			}

		/**
		 * Create an Account object
		 * 
		 * @param _type - the account type to create
		 */
		Account(com.abc.AccountType _type)
			{
				this();
				this.accountType = _type;
				this.transactions = new TransactionList(this);
			}

		/**
		 * Post a transaction to the account. Adds the transaction to the
		 * transaction list and update the balance.
		 * 
		 * @param _t the transaction to post
		 */
		private void postTransaction(Transaction _t)
			{
				// make sure we record the first transaction date for calculating interest on 
				// the account
				if (this.first_transaction_dt == null )
					this.first_transaction_dt = _t.getTransactionDate();
				
				// add to the transaction list for the account
				transactions.add(_t);

				// update the balance
				this.balance = this.sumTransaction(_t, this.balance);

				// return
				return;

			}

		/**
		 * Deposit funds into an existing account
		 * 
		 * @param _amount the amount to deposit into the account
		 */
		public Transaction deposit(double _amount) 
			{
				// if the amount is LE zero, then this should throw
				// an exception
				if (_amount <= 0)
					{
						throw new IllegalArgumentException("amount must be greater than zero");
					}

				//create the transaction
				Transaction t = new DepositTransaction(_amount);

				// post the transaction
				this.postTransaction(t);

				// return the transaction for reference
				return t;
			}

		/**
		 * Withdraw funds from an existing account
		 * 
		 * @param _amount - the amount to withdraw from the account
		 * @throws AccountOperationException
		 * @return return the withdrawal transaction for reference
		 */
		public Transaction withdraw(double _amount)
			{
				// if the amount of the withdrawal is LE zero, throw an
				// exception.
				if (_amount <= 0)
					{
						throw new IllegalArgumentException("amount must be greater than zero");
					}
				else if (this.balance - _amount < 0)  // don't let the balance go negative
					{
						throw new IllegalArgumentException("the withdrawal would create a negative balance");
					}

				Transaction t = new WithdrawalTransaction(this, _amount);

				// post the transaction
				this.postTransaction(t);

				// return the transaction for reference
				return t;

			}

		/**
		 * Transfer funds from one account to another
		 * 
		 * @param _to_account the account to transfer to
		 * @param _amount the amount to withdraw from the account
		 * @return return the transfer out transaction for reference
		 */
		public Transaction transfer(Account _to_account, double _amount)
			{
				// if the amount of the withdrawal is LE zero, throw an
				// exception.
				if (_amount <= 0)
					{
						throw new IllegalArgumentException("amount must be greater than zero");
					}
				else if (this.balance - _amount < 0)  // don't let the balance go negative
					{
						throw new IllegalArgumentException("the withdrawal would create a negative balance");
					}

				// TODO: Transfer validation 

				Transaction t = new TransferOutTransaction(_to_account, _amount);

				// post the transaction
				this.postTransaction(t);

				// transfer in to the target account
				_to_account.transfer_in(this, _amount);

				// return the transaction for reference
				return t;
			}

		/**
		 * Transfer coming in from another account
		 * 
		 * @param _amount - the amount to transfer to this account account
		 * @return return the transfer in transaction for reference
		 */
		private Transaction transfer_in(Account _from_account, double _amount)
			{
				// if the amount of the transfer is LE zero, throw an exception.
				if (_amount <= 0)
					{
						throw new IllegalArgumentException("amount must be greater than zero");
					}

				Transaction t = new TransferInTransaction(_from_account, _amount);

				// post the transaction
				this.postTransaction(t);

				// return the transaction for reference
				return t;
			}

		/**
		 * Apply interest to the account
		 * 
		 * @param _amount the amount of interest to apply
		 */
		public Transaction apply_interest(double _amount)
			{
				Transaction t = new InterestTransaction(_amount);

				// post the transaction
				this.postTransaction(t);

				// Update the last interest posting date
				this.last_interest_dt = t.getTransactionDate();
				
				// return the transaction for reference
				return t;
			}

		/**
		 * Sum a transaction with an amount. Provides a consistent method for
		 * adding up transactions. Used in all Account operations to calculate
		 * the rolling balance.
		 * 
		 * @param _t The transaction that will be addeded
		 * @param _amount the amount to add to
		 * @return the total <i>Transaction::amount + _amount</i>
		 */
		public double sumTransaction(Transaction _t, double _amount)
			{
				if (_t.getTransType().getOpCode() == TransactionType.OpCode.DEBIT)
					_amount += _t.amount;
				else if (_t.getTransType().getOpCode() == TransactionType.OpCode.CREDIT)
					_amount -= _t.amount;

				return _amount;
			}

		/**
		 * Refresh the balance on the account.
		 * 
		 */
		public double refreshBalance()
			{
				// sum the transactions
				double amount = 0.0;
				for (Transaction t : transactions)
					amount = sumTransaction(t, amount);

				// update the balance
				this.balance = amount;

				//return the balance
				return this.balance;
			}

		/**
		 * Return the account type of this account
		 * 
		 * @return the type defined for this account
		 * @see AccountType
		 */
		public AccountType getType()
			{
				return accountType;
			}

		/**
		 * Get the list of transactions against this account
		 * 
		 * @return the transaction list
		 */
		public TransactionList getTransactionList()
			{
				return transactions;
			}

		/**
		 * Get the current balance on the account.
		 * 
		 * @param _refresh <i>true</i> if we should recalculate the balance or
		 *        <i>false</i> if we should not
		 * @return the balance
		 */
		public double getBalance(boolean _refresh)
			{

				if (_refresh)
					return this.refreshBalance();
				return balance;
			}

		/**
		 * Get the current state of the balance
		 * 
		 * @return the balance_state
		 */
		public DataState getBalanceState()
			{
				return balance_state;
			}

		/**
		 * Set the current state of the balance.
		 * 
		 * @param balance_state the balance_state to set
		 */
		protected void setBalanceState(DataState balance_state)
			{
				this.balance_state = balance_state;
			}

		/**
		 * Get a property from the account properties map.
		 * 
		 * @param _key the key of the property to look for
		 * 
		 * @return a string representing the property value or <i>null</i> if no
		 *         such property exists
		 */
		protected String getProperty(String _key)
			{
				if (this.props == null)
					loadProperties();

				return this.props.getProperty(_key);
			}

		/**
		 * Load the checking account properties.
		 * <p>
		 * This is a VERY simple implementation of the properties for bank
		 * accounts. It allows a perpetual call to make sure that properties are
		 * always loaded.
		 * 
		 * @throws IOException
		 */
		protected void loadProperties()
			{
				// if the properties are already loaded just use what is there.
				if (this.props != null)
					return;

				File props_file = new File(this.getPropertiesFilename());

				// create the new properties object to load
				this.props = new Properties();

				if (props_file.exists())
					{
						InputStream input = null;

						try
							{
								// create an input stream from the file
								input = new FileInputStream(props_file);

								// load the properties file into the properties object
								this.props.load(input);

							}
						catch (IOException ex) 	// something drastic: just create default properties
							{
								// log the exception
								Logger.logException(ex);

								// create the default properties temporarily so we can continue
								resetProperties(this.props);
							}
						finally
							{
								if (input != null)
									{
										try
											{
												input.close();
											}
										catch (IOException _ex)	// something drastic: ignore for now
											{
												Logger.logException(_ex);
											}
									}
							}

					}
				else
					{

						// reset the properties to the default values
						this.resetProperties(this.props);

						OutputStream output = null;

						try
							{
								// create an input stream from the file
								output = new FileOutputStream(props_file);

								// load the properties file
								this.props.store(output, null);

							}
						catch (IOException ex) 	// something drastic: just create default properties
							{
								// log the exception
								Logger.logException(ex);

								// create the default properties temporarily so we can continue
								resetProperties(this.props);
							}
						finally
							{
								if (output != null)
									{
										try
											{
												output.close();
											}
										catch (IOException _ex)	// something drastic: ignore for now
											{
												Logger.logException(_ex);
											}
									}
							}

					}
			}

		/**
		 * Reset the properties to default values.
		 * <p>
		 * This method should at a bare minimum, create a new
		 * java.util.Properties object and make sure it is available through the
		 * member variable <i>props</i>.
		 * 
		 * 
		 */
		protected abstract void resetProperties(Properties _p);

		/**
		 * Get the properties filename associated with the subclass. This method
		 * is called in the subclassed Account object when the loadProperties
		 * needs to open the properties file for loading.
		 * <p>
		 * This enables different implementations to have different names for
		 * each subclass or names within the subclass. It allows the subclass to
		 * determine the name.
		 * 
		 * @return string containing the name and/or full qualified pathname for
		 *         the properties file.
		 */
		protected abstract String getPropertiesFilename();

		/**
		 * Calculate interest on the account. Each subclass is responsible for
		 * creating a method of calculating interest.
		 * 
		 * @return the interest on the current balance
		 * 
		 */
		public abstract double calculateInterest();

	}
