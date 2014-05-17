/**
 * AccountType.java
 */
package com.abc;

/**
 * An enumeration of all the possible transaction types. It creates type safety
 * for creating transactions within the banking application. 
 * <p>
 * We also use an OpCode to determine what effect a transaction will have on an
 * account balance.
 * 
 * @author Jeff
 * 
 */
public enum TransactionType
	{

		UNKNOWN(-1, "UNKNOWN", OpCode.NONE),				// the unknown soldier
		DEPOSIT(0, "DEPOSIT", OpCode.DEBIT), 				// customer initiated deposit
		WITHDRAWAL(1, "WITHDRAWAL", OpCode.CREDIT), 		// customer initiated withdrawal
		TRANSFER_IN(2, "TRANSFER_IN", OpCode.DEBIT),		// an account transfer coming in
		TRANSFER_OUT(3, "TRANSFER_OUT", OpCode.CREDIT),		// an account transfer going out
		INTEREST(4, "INTEREST", OpCode.DEBIT),				// interest accrued
		MULTI_LEG(5, "MULTI_LEG", OpCode.NONE);				// multi-legged transaction (used only for transfers )

		public enum OpCode
			{
				DEBIT, CREDIT, NONE
			};

		private int		code;
		private String	name;
		private OpCode	op_code;

		/**
		 * Create an TransactionType enum
		 * 
		 * @param _value - the value of the enum
		 * @param _name - the name of the enum
		 * @param _debit - whether or not the transaction amount should debit or
		 *        credit the customers balance
		 */
		private TransactionType(int _value, String _name, OpCode _op)
			{
				code = _value;
				name = _name;
				op_code = _op;
			}

		/**
		 * Get the code associated with the TransactionType
		 * 
		 * @return - the transaction type code
		 */
		public int getCode()
			{
				return code;
			}

		/**
		 * Get the name associated with the TransactionType
		 * 
		 * @return - the transaction type name
		 */
		public String getName()
			{
				return name;
			}

		@Override
		public String toString()
			{
				final StringBuilder sb = new StringBuilder();
				sb.append("TransactionType: ");
				sb.append(name).append('(');
				sb.append(')');
				return sb.toString();
			}

		/**
		 * Get the value of the opcode flag
		 * 
		 * @return the value of the op code flag
		 */
		public OpCode getOpCode()
			{
				return this.op_code;
			}
	}
