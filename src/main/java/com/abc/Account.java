package com.abc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		 * Deposit funds into an existing account
		 * 
		 * @param amount - the amount to deposit into the account
		 */
		public void deposit(double amount) throws IllegalArgumentException
			{
				// if the amount is LE zero, then this should throw
				// an exception
				if (amount <= 0)
					{
						throw new IllegalArgumentException("amount must be greater than zero");
					}
				// otherwise
				else
					{

						transactions.add(new DepositTransaction(this, amount));
					}
			}

		/**
		 * Withdraw funds from an existing account
		 * 
		 * @param amount - the amount to withdraw from the account
		 */
		public void withdraw(double amount) throws IllegalArgumentException
			{
				// if the amount of the withdrawal is LE zero, throw an
				// exception.
				if (amount <= 0)
					{
						throw new IllegalArgumentException("amount must be greater than zero");
					}
				else
					// create a new transaction to record the amount of the
					// withdrawal
					{
						transactions.add(new WithdrawalTransaction(this, -amount));
					}
			}

		/**
		 * sum the transactions for this account
		 * 
		 * @return the sum of all transactions
		 */
		public double sumTransactions()
			{
				double amount = 0.0;
				for (Transaction t : transactions)
					amount += t.amount;
				return amount;
			}

		/**
		 * Refresh the balance on the account.
		 * 
		 */
		public void refreshBalance()
			{
				double amount = 0.0;
				for (Transaction t : transactions)
					amount += t.amount;
				balance = amount;
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
		 * @return the balance
		 */
		public double getBalance()
			{
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
		 * Get the properties filename associated with the subclass. This method is called
		 * in the subclassed Account object when the loadProperties needs to open the 
		 * properties file for loading. 
		 * <p>
		 * This enables different implementations to have different names for each subclass
		 * or names within the subclass. It allows the subclass to determine the name.
		 * 
		 * @return string containing the name and/or full qualified pathname
		 * 		   for the properties file.
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
