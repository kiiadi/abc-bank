package com.abc;


public class AccountNumber {
	 	
		private static int AccountNumber =1 ;
		
		public static int getPreviousAcctNumber(){
	    	return AccountNumber;
	    }
	    
	    public static void setNewAccountNumber(int acctNo){
	    	AccountNumber= acctNo;
	    }
}
