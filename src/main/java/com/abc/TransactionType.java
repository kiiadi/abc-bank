/**
 * AccountType.java
 */
package com.abc;

/**
 * An enumeration of all the possible transaction types. It creates type safety for
 * creating transactions within the banking application.
 * 
 * @author Jeff
 * 
 */
public enum TransactionType {

	CUSTOMER_DEPOSIT(0, "CUSTOMER_DEPOSIT"), 			// customer initiated deposit
	CUSTOMER_WITHDRAWAL(1,"CUSTOMER_WITHDRAWAL"), 		// customer initiated withdrawal
	ACCOUNT_TRANSFER(2, "ACCOUNT_TRANSFER" ),			// an account transfer
	INTEREST_ACCRUED(3, "INTEREST_ACCRUED" );			// interest accrued
	
	private int code;
	private String name;
	
	/**
	 * Create an TransactionType enum
	 * @param _value
	 * @param _name
	 */
	private TransactionType( int _value, String _name ) {
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
	
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TransactionType: ");
        sb.append(name).append('(');
        sb.append(')');
        return sb.toString();
    }

	
}
