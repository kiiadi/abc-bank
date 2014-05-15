/**
 * 
 */
package com.abc;


/**
 * A class representing a commit exception. These objects are created when transactions are asked to 
 * commit and if any errors occur, the developer can poll for this unique exception.
 * 
 * @author Jeff
 *
 */
public class CommitException extends Exception
	{

	/**
	 * Default implementation for now
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * Create the exception with a message.
	 * @param _msg - the message to hold
	 */
	public CommitException( String _msg ) 
		{
			super( _msg );
		}
	}
