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

	UNKNOWN(-1,"UNKNOWN", false),			// the unknown soldier
	DEPOSIT(0, "DEPOSIT", true), 			// customer initiated deposit
	WITHDRAWAL(1,"WITHDRAWAL", false), 		// customer initiated withdrawal
	TRANSFER_IN(2, "TRANSFER_IN", true ),	// an account transfer coming in
	TRANSFER_OUT(3,"TRANSFER_OUT", false ),	// an account transfer going out
	INTEREST(4, "INTEREST", true ),			// interest accrued
	MULTI_LEG(5, "MULTI_LEG",false);		// multi-legged transaction (used only for transfers
	
	private int code;
	private String name;
	private boolean debit;
	
	/**
	 * Create an TransactionType enum
	 * @param _value - the value of the enum 
	 * @param _name - the name of the enum
	 * @param _debit - whether or not the transaction amount should debit or credit the
	 * customers balance
	 */
	private TransactionType( int _value, String _name, boolean _debit ) {
		code = _value;
		name = _name;
		debit = _debit;
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

    /**
     * get the value of the debit flag
     * 
     * @return the value of the debit flag
     */
    public boolean isDebit() 
    	{
    		return this.debit;
    	}
}
