/**
 * 
 */
package com.abc;


/**
 * A class representing a generic exception for a bank account. 
 * 
 * @author Jeff
 */
public class AccountOperationException extends Exception
	{

	/**
	 * Default implementation for now
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * Create the exception with a message.
	 * @param _msg - the message to hold
	 */
	public AccountOperationException( String _msg ) 
		{
			super( _msg );
		}
	}
