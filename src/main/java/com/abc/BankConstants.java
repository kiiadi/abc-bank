package com.abc;

public class BankConstants {

	enum TransactionType 
	{
		DEPOSIT("deposit"), WITHDRAW("withdraw"),INTEREST("interest");
		TransactionType(String value_)
		{
			_value = value_;
		}
		public String getValue() { return _value; }
		private String _value;
	}
	
	enum AccountType 
	{
		CHECKING ("Checking") , SAVINGS("Savings") ,  MAXI_SAVINGS("Maxi Savings");
		AccountType(String value_)
		{
			_value = value_;
		}
		public String getValue() { return _value; }
		private String _value;
		
	}
}
