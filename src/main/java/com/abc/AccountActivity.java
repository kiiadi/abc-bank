package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountActivity {

	private List<Transaction> transactions;
    private double currBalance ;

    public double getCurrBalance() {
		return currBalance;
	}

	public AccountActivity(final double startingBalance) {
        this.transactions = Collections.synchronizedList(new ArrayList<Transaction>());
        this.currBalance = startingBalance ;
    }
	
	public  void processTransaction(final TransactionType type , final double amount) {
		Transaction tran = new Transaction(type, amount) ;
		synchronized(this) {
			transactions.add(tran);
			currBalance += ( amount * ( type == TransactionType.Deposit ? 1 : -1 )) ;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer s = new StringBuffer() ;
		for ( Transaction t : transactions ) {
			s.append(t.toString()) ;
		}
		return s.toString();
	}
	
	public List<Transaction> transactions() {
		/* Do not let current activity escape from current thread  */
		return new ArrayList<Transaction>(transactions) ;
		
	}


}
