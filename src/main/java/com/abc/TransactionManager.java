package com.abc;
import static java.lang.Math.abs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import com.abc.BankConstants.TransactionType;
import java.util.Map;
import java.util.HashMap;

import java.util.LinkedList;
public class TransactionManager {
	private InterestRateRuleEngine interestRuleEngine = new InterestRateRuleEngine();
	// All transactions 
	private List<Transaction> _transactions = new LinkedList<Transaction>() ;
	// Below are some duplicate collections for performance
	// Lets put Transaction in some mor maps
	// 1. Account to list Of transaction
	// 2. customer to list of Transaction 
	private Map<Integer, List< Transaction>> _customerTransactions  = new HashMap<Integer , List<Transaction>>();
	private Map<Integer, List<Transaction>> _accountTransactions  = new HashMap<Integer, List<Transaction>>();

	
	public void logTransaction(Integer customerId_, Integer accountId_ ,  Transaction transaction_)
	{
		_transactions.add(transaction_);
		List<Transaction> customerTransaction  = _customerTransactions.get(customerId_);
		if ( customerTransaction == null )
		{
			customerTransaction = new LinkedList<Transaction>();
			_customerTransactions.put(customerId_, customerTransaction);
		}
		customerTransaction.add(transaction_);
		
		List<Transaction> accountTransaction  = _accountTransactions.get(accountId_);
		if ( accountTransaction == null )
		{
			accountTransaction = new LinkedList<Transaction>();
			_accountTransactions.put(accountId_, accountTransaction);
		}
		accountTransaction.add(transaction_);
	}
	
	public void transaction(Customer customer_ , Account account_, TransactionType transactionType_ , BigDecimal amount_) {
        if (amount_ == null || amount_.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } 
        switch(transactionType_)
        {
        case WITHDRAW :
        		if( account_.getAmount().compareTo(amount_) < 0 )
        			throw new IllegalArgumentException("account has less amount");
        		account_.setAmount(account_.getAmount().subtract(amount_));
        		break;
        case DEPOSIT :
        case INTEREST :
        	account_.setAmount(account_.getAmount().add(amount_));
        	break;
        }
        Transaction transaction = new Transaction(getNextTransctionId(),amount_, account_.getId(), customer_.getId(), Calendar.getInstance().getTime(), transactionType_);
        logTransaction(customer_.getId(), account_.getId(), transaction);
    }



    
    public BigDecimal totalInterestEarned(Customer customer_) {
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : customer_.getAccounts())
            total = total.add(intersetEarned(a));
        return total;
    }

    public void applyIntereset(Customer customer_) {
    {
    	for (Account a : customer_.getAccounts()) {
    		BigDecimal intereset = interestRuleEngine.calculate(a);
    		if ( intereset.compareTo(BigDecimal.ZERO) > 0 )
    		{
    			transaction(customer_, a, TransactionType.INTEREST, intereset);
    		}
    	}
    }
    }
    public String getStatement(Customer customer_) {
        StringBuilder statement = new StringBuilder();
        statement.append( "Statement for ").append(customer_.getName()).append( "\n");
        BigDecimal total = BigDecimal.ZERO;
        for (Account a : customer_.getAccounts()) {
        	statement.append(statementForAccount(a)).append("\n");
        	total = total.add(a.getAmount());
        }
        statement.append("\nTotal In All Accounts " + toDollars(total));
        return statement.toString();
    }

    private BigDecimal intersetEarned(Account a) {
        List<Transaction> transactions = _accountTransactions.get(a.getId());
        BigDecimal total = BigDecimal.ZERO;
        
        if ( transactions != null && !transactions.isEmpty()){
        //Now total up all the transactions
        
        for (Transaction t : transactions) {
        	if( t.getTransactionType() == TransactionType.INTEREST)
            total = total.add(t.getAmount());
        }
        }
        return total;
    	
    	
    }
    
    private String statementForAccount(Account a) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.getAccountType().getValue()).append(" Account").append("\n");
        List<Transaction> transactions = _accountTransactions.get(a.getId());
        BigDecimal total = BigDecimal.ZERO;
        
        if ( transactions != null && !transactions.isEmpty()){
        //Now total up all the transactions
        
        for (Transaction t : transactions) {
        	sb.append(t.getTransactionType().getValue()).append(" ").append(toDollars(t.getAmount())).append("\n");
        	switch( t.getTransactionType())
        	{
        	case WITHDRAW :
        		total = total.subtract(t.getAmount());
        		break;
        	case DEPOSIT:
        	case INTEREST:
        		total = total.add(t.getAmount());
        		break;
        	}   
        }
        }
        sb.append("Total ").append(toDollars(total));
        return sb.toString();
    }

    private String toDollars(BigDecimal d){
        return  d.setScale(2, RoundingMode.HALF_EVEN).toString();
    }


    private Integer getNextTransctionId()
    {
    	return _transactions.size() + 1;
    }
}
