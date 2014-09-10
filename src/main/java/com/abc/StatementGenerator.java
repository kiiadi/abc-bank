package com.abc;

import static java.lang.Math.abs;

public class StatementGenerator {

	public StatementGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static String print(Transaction tran) {
		return String.format("%s %s %s \n", tran.getTransactionDate().toString(),tran.getType().toString(),toDollars(tran.getAmount()));
	}
	
	public static String print(AccountActivity activity) {
		return activity.toString();
	}
	
	public static String print(Account acct) {
		StringBuffer s = new StringBuffer() ;
		for ( Transaction t : acct.transactions() ) {
			s.append(print(t)) ;
		}
		s.append("\n");
		s.append("Total ");
		s.append(Double.toString(acct.getCurrBalance()));
		s.append("\n");
		return s.toString();
	}
	
	public static String print(Customer cust) {
        StringBuilder statement = new StringBuilder() ;
        statement.append("Statement for ") ;
        statement.append(cust.getName()) ;
        statement.append("\n") ;
    
        for (Account acct : cust.accounts()) {
        	statement.append(print(acct)) ;
            statement.append("\n");
        }
        statement.append("\nTotal In All Accounts ");
        statement.append(toDollars(cust.totalBalance()));
        return statement.toString();
    }

	public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
	
	public static printCustomerSummary(Bank bank) {
		
	}
}
