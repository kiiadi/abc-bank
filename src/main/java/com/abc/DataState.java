/**
 * 
 */
package com.abc;

/**
 * A generalized data state enumeration. This has multiple purposes within 
 * the application. 
 * 
 * @author Jeff
 *
 */
public enum DataState {
	UNKNOWN(-1,"UNKNOWN"),			// unknown soldier
	CLEAN(0, "CLEAN"), 				// clean data
	DIRTY(1,"DIRTY"), 				// dirty data
	UNPROCESSED(2, "UNPROCESSED" ),	// unprocessed data
	PROCESSED(3,"PROCESSED" );		// processed data
	
	private int code;
	private String name;
	
	/**
	 * Create an DataState enum
	 * 
	 * @param _value - the value of the enum 
	 * @param _name - the name of the enum
\	 * customers balance
	 */
	private DataState( int _value, String _name ) {
		code = _value;
		name = _name;
	}
	
	/**
	 * Get the code associated with the TransactionType
	 * @return - the transaction type code
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * Get the name associated with the TransactionType
	 * @return - the transaction type name
	 */
	public String getName() {
		return name;
	}


}
