package com.abc;

public interface BankConstants {

	enum TransactionType 
	{
		DEPOSIT("deposit"), WITHDRAWAL("withdrawal"),INTEREST("interest");
		TransactionType(String value)
		{
			this.value = value;
		}
		public String getValue() { return value; }
		private String value;
	}
	
	enum AccountType 
	{
		CHECKING ("Checking") , SAVINGS("Savings") ,  MAXI_SAVINGS("Maxi Savings");
		AccountType(String value_)
		{
			value = value_;
		}
		public String getValue() { return value; }
		private String value;
		
	}

	//Keeping it simple interest that will be calculated divide by 365
	// even for leap year we will pay here one more day interest 
	public int DAYS_IN_A_YEAR = 365;
}
