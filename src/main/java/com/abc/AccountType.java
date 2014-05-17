/**
 * AccountType.java
 */
package com.abc;

/**
 * An enumeration of all the possible account types. This allows for type safety when creating
 * accounts or distinguishing between accounts.
 * 
 * @author Jeff
 * 
 */
public enum AccountType {

	CHECKING(0, "CHECKING"), 			// a checking account
	SAVINGS(1,"SAVINGS"), 				// a savings account
	MAXISAVINGS(2, "MAXISAVINGS" ); 	// a maxi-account
	
	private int code;
	private String name;
	
	/**
	 * Create an AccountType enum
	 * @param _value
	 * @param _name
	 */
	private AccountType( int _value, String _name ) {
		code = _value;
		name = _name;
	}
	
	/**
	 * Get the code associated with the AccountType
	 * @return - the account type code
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * Get the name associated with the AccountType
	 * @return - the account type name
	 */
	public String getName() {
		return name;
	}
	
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AccountType: ");
        sb.append(name).append('(');
        sb.append(')');
        return sb.toString();
    }

	
}
