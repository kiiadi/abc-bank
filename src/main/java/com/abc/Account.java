package com.abc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

/**
 * The Class Account.
 */
public class Account {
	
	/* ------ Various Account Text Fields --------------------------*/
	
	/** The constant WITHDRAWAL_TEXT */
	private static final String WITHDRAWAL_TEXT="withdrawal";
	
	/** The constant DEPOSIT_TEXT */
	private static final String DEPOSIT_TEXT="deposit";	
	
	/** The constant TOTAL_TEXT */
	private static final String TOTAL_TEXT="Total";	
		
	/** The constant INTEREST_EARNED_TEXT */
	private static final String INTEREST_EARNED_TEXT="Interest Earned";	
	
	
	/** The Constant for CHECKING accountType text */
	private static final String CHECKING_ACCOUNT_TEXT="Checking";
	
	/** The Constant for SAVINGS accountType text */
	private static final String SAVINGS_ACCOUNT_TEXT="Savings";
	
	/** The Constant for MAXI_SAVINGS accountType text */
	private static final String MAXI_SAVINGS_ACCOUNT_TEXT="Maxi-Savings";
	
	/** The Constant for the UNKNOWN accountType text */
	private static final String UNKNOWN="Unknown";
	
	
	/* ------ Transaction Input Error Values --------------------------*/
	
	/** The Constant for the (amount =< 0), input error */
	public static final int AMOUNT_MUST_BE_GREATER_THAN_ZERO_ERROR=-1; 
	
	/** The Constant for Funds not available error */
	public static final int FUNDS_NOT_AVAILABLE_ERROR=-2;
	

	/* ------ AccountType values --------------------------*/
	
    /** The Constant for CHECKING accountType index. */
    public static final int CHECKING=0;
    
    /** The Constant for SAVINGS accountType index. */
    public static final int SAVINGS=1;
    
    /** The Constant for MAXI_SAVINGS accountType index. */
    public static final int MAXI_SAVINGS=2;
    
    /** The Constant for MAX_ACCOUNT_TYPE_SIZE, used in Customers to determine size of Accounts Map */
    public static final int MAX_ACCOUNT_TYPE_SIZE=3;   
    
     
    /* ------ Used by Interest Rate Calculations --------------------------*/
    
    /** The Constant for NON_WITHDRAWAL_PERIOD_DAY_COUNT, used as a check in Maxi_Savings accounts interest formulas */
    public static final int NON_WITHDRAWAL_PERIOD_DAY_COUNT=10;
    
    /** The Constant for SAVINGS_THRESHOLD */
    public static final double SAVINGS_THRESHOLD=1000.0;
    
    /** The Constant for ACCRUED_DAILY_INCL_WEEKENDS */
    private static final int ACCRUED_DAILY_INCL_WEEKENDS=365;
    
    
    /** The Constant for DEFAULT_ACCT_INT_RT */
    private static final double DEFAULT_ACCT_INT_RT=0.001;
    
    /** The Constant for CHECKING_ACCT_INT_RT */
    private static final double CHECKING_ACCT_INT_RT=DEFAULT_ACCT_INT_RT;
    
    /** The Constant for SAVINGS_ACCT_BELOW_THRESHOLD_INT_RT */
    private static final double SAVINGS_ACCT_BELOW_THRESHOLD_INT_RT=DEFAULT_ACCT_INT_RT;
    
    /** The Constant for SAVINGS_ACCT_ABOVE_THRESHOLD_INT_RT */
    private static final double SAVINGS_ACCT_ABOVE_THRESHOLD_INT_RT=0.002;
    
    /** The Constant for MAXI_SAVINGS_ACCT_INT_RT */
    private static final double MAXI_SAVINGS_ACCT_INT_RT=0.05;
    
    /*------------------------------------------------------------*/
        
    /** The account type. */
    private final int accountType;
    
    /** The account balance */
    private double balance = 0.0;
   
    /** The transactions list. */
    public List<Transaction> transactions = null;    

    /** The static Date for the date of the last withdrawal, used for MAXI_SAVINGS interest */
    private Date lastWithdrawalDate = null;    

    
    /**
     * Instantiates a new account for a valid accountType, and initializes that account.
     * We will use a doubly linked list to store the transactions.
     *
     * @param accountType the account type
     * @throws IllegalArgumentException for an invalid accountType
     */
    public Account(int accountType) throws IllegalArgumentException {
    	if ((accountType < 0) || (accountType >= MAX_ACCOUNT_TYPE_SIZE)) {
            throw new IllegalArgumentException("ERROR : accountType[" + accountType + "] is invalid");
        } 
        this.accountType = accountType;
        this.balance = 0.0;
                
        this.transactions = (List<Transaction>)(Collections.synchronizedList(new LinkedList<Transaction>()));
     
        this.lastWithdrawalDate = null;
    }    

    
    /**
     * Gets the account type.
     *
     * @return the account type
     */
    public int getAccountType() {
        return accountType;
    }
    
    
    /**
     * Gets the associated text for this accountType
     * @return the associated accountType text, or a some default text
     */
    private String getAccountTypeText() {
    	String retStr = null;
    		
	    //Translate to pretty account type
	    switch (getAccountType()) {
	        case Account.CHECKING: {
	            retStr = Account.CHECKING_ACCOUNT_TEXT;
	            break;
	        }    
	        case Account.SAVINGS: {
	            retStr = Account.SAVINGS_ACCOUNT_TEXT;
	            break;
	        }    
	        case Account.MAXI_SAVINGS: {
	            retStr = Account.MAXI_SAVINGS_ACCOUNT_TEXT;
	            break;
	        }
	        default: {
	        	//In theory, we should never see this...
	        	retStr = Account.UNKNOWN;
	        	break;
	        }
	    }
	    
	    return retStr;
    }

    
    /**
     * deposit.   
     *
     * @param amount the amount to deposit
     * @return 0 on success; else non-zero to denote an error
     */
    public synchronized int deposit(double depositAmount) {     
        if (depositAmount <= 0) {
        	return AMOUNT_MUST_BE_GREATER_THAN_ZERO_ERROR;
        } else {  
        	Transaction deposit = new Transaction(depositAmount, Transaction.DEPOSIT);
            transactions.add(deposit);
            balance += depositAmount;
            return 0;
        }
    }

    
    /**
     * withdraw.    
     *
     * @param amount the amount to withdraw
     * @return 0 on success; else non-zero to denote an error
     */
    public synchronized int withdraw(double withdrawalAmount) {    
        if (withdrawalAmount <= 0) { 
        	return AMOUNT_MUST_BE_GREATER_THAN_ZERO_ERROR;
        } else {
        	if (balance >= withdrawalAmount)
        	{
        	    Transaction withdrawal = new Transaction(withdrawalAmount, Transaction.WITHDRAWAL);
                transactions.add(withdrawal);
                balance -= withdrawalAmount;
                this.lastWithdrawalDate = withdrawal.getTransactionDate();
                return 0;
        	} else {
        	    return FUNDS_NOT_AVAILABLE_ERROR;
        	}
        }
    }
    

    /**
     * Calculates the compound interest.          
     *
     * @param balance the balance
     * @param annualInterestRate the annual interest rate (i.e. 4.2% = .042) 
     * @param period the number of periods (i.e. 365 is for daily incl W/E)
     * @return the compound interest of the balance
     */       
    private synchronized double compoundInterest(double balance, double annualInterestRate, int period)
    {
        double interest = (balance * Math.pow((1 + annualInterestRate/365), 365)) - balance;
        
        return interest;
    }

    
   /**
    * Compound interest earned, according to accountType.    
    *  <p>
    * Different accounts have interest calculated in different ways:<br>
    * Checking accounts** have a flat rate of {@value #CHECKING_ACCT_INT_RT}%<br>
    * Savings accounts** have a rate of {@value #SAVINGS_ACCT_BELOW_THRESHOLD_INT_RT}% for the first ${@value #SAVINGS_THRESHOLD} then {@value #SAVINGS_ACCT_ABOVE_THRESHOLD_INT_RT}%<br>
    * Maxi-Savings accounts** have an interest rate of {@value #MAXI_SAVINGS_ACCT_INT_RT}%, assuming no withdrawals in the past {@value #NON_WITHDRAWAL_PERIOD_DAY_COUNT} days; otherwise {@value #DEFAULT_ACCT_INT_RT}%
    * <br><br>
    * NOTE:
    * Interest rates should accrue daily (incl. weekends), rates above are per-annum 
    * 
    * @param accrualPeriod the accrual period for the interest earned formula, (i.e. {@value #ACCRUED_DAILY_INCLUDING_WEEKENDS} - daily including W/E)
    * @return the interest earned, as a double
    */    
    private synchronized double compoundInterestEarned(int accrualPeriod) { 
    	        
        double amount = getBalance();

        switch (accountType) {
            case CHECKING: {     
            	//**Checking accounts** have a flat rate of 0.1%
            	
            	//return amount * 0.001; 
            	return compoundInterest(amount, CHECKING_ACCT_INT_RT, accrualPeriod);
            }
            case SAVINGS: {      
            	//**Savings accounts** have a rate of 0.1% for the first $1,000 then 0.2%
            	
                if (amount <= SAVINGS_THRESHOLD) {
                    //return amount * 0.001;
                    return compoundInterest(amount, SAVINGS_ACCT_BELOW_THRESHOLD_INT_RT, accrualPeriod);
                }    
                else {
                    //return 1.0 + ((amount-1000.0) * 0.002);
                	double interestUnderSavingsThreshold = compoundInterest(SAVINGS_THRESHOLD, SAVINGS_ACCT_BELOW_THRESHOLD_INT_RT, accrualPeriod);    
                	double interestAboveSavingsThreshold = compoundInterest((amount - SAVINGS_THRESHOLD), SAVINGS_ACCT_ABOVE_THRESHOLD_INT_RT, accrualPeriod);
                	
                	return (interestUnderSavingsThreshold + interestAboveSavingsThreshold);
                }
            }
            case MAXI_SAVINGS: {    
            	//**Maxi-Savings accounts** have an interest rate of 5%, assuming no withdrawals in the past 10 days; otherwise 0.1%
            	
            	boolean noWithdrawalsInLastXDays = (lastWithdrawalDate == null) ? true : DateProvider.isGreaterThanXDaysAgo(lastWithdrawalDate, NON_WITHDRAWAL_PERIOD_DAY_COUNT);
            	
            	if (noWithdrawalsInLastXDays) {
	                //return amount * 0.05;
	                return compoundInterest(amount, MAXI_SAVINGS_ACCT_INT_RT, accrualPeriod);
            	}
            	else {
            		//return amount * 0.001;
            		return compoundInterest(amount, DEFAULT_ACCT_INT_RT, accrualPeriod);
            	}
            }    
            default: {
                //return amount * 0.001;
                return compoundInterest(amount, DEFAULT_ACCT_INT_RT, accrualPeriod);
            }    
        }
    }
    
    
    /**
     * Helper method for daily accrued, (including weekends), interest earned    
     * 
     * @return the compound interest earned, as a double
     */    
     public synchronized double compoundInterestEarned() {
    	 return compoundInterestEarned(ACCRUED_DAILY_INCL_WEEKENDS);
     }
       
       
    /**
     * Gets the account balance.          
     *
     * @return the account balance
     */       
    public synchronized double getBalance() {
        return balance;
    }
    
    
    /**
     * Gets the last withdrawal date.         
     *
     * @return the last withdrawal date
     */       
    public synchronized Date getLastWithdrawalDate() {
        return lastWithdrawalDate;
    }
    
  
    /**
     * Statement for this account.    
     *
     * @param acctBuff the StringBuffer used to build the account statement
     * @param showInterestEarned a flag to show the interest earned
     * @return the String representation of this Account
     * 
     */
    public synchronized double statementForAccount(StringBuffer acctBuff, boolean showInterestEarned) {
    
        //Add account heading
        acctBuff.append(getAccountTypeText());
        acctBuff.append(" Account");
        acctBuff.append("\n");
        
        //Now print out the individual transactions
        for (Transaction t : transactions) {
        	double transactionAmount = t.getTransactionAmount();
        	int transactionType = t.getTransactionType();
        	
        	acctBuff.append(" ");
        	acctBuff.append(((transactionType == Transaction.WITHDRAWAL) ? WITHDRAWAL_TEXT : DEPOSIT_TEXT));
        	acctBuff.append(" ");
        	acctBuff.append(toDollars(transactionAmount));
        	acctBuff.append("\n");
        }
        
        //Add the total
        acctBuff.append(TOTAL_TEXT);
        acctBuff.append(" ");
        acctBuff.append(toDollars(this.getBalance()));
        acctBuff.append(" ");
        
        //Add the interest earned, if requested
        if (showInterestEarned) {
            acctBuff.append(INTEREST_EARNED_TEXT);
            acctBuff.append(" ");
            acctBuff.append(toDollars(this.compoundInterestEarned()));
        }
        acctBuff.append("\n");
        
        return getBalance();
    }
    

    /**
     * toDollars
     *
     * @param d the d
     * @return the string representation of the input
     */
    public static String toDollars(double d) {
        return String.format("$%,.2f", Math.abs(d));
    }
    
    
    /**
     * Round to nearest tenthes.
     *
     * @param d the d
     * @return the string representation of the input
     */
    public static String toTenthes(double d) {
        return String.format("%,.2f", Math.abs(d));
    }

        
    /**
     * Rebuild the balance from the list of transactions, and reset lastWithDrawalDate
     * This can be used to start up a system 
     *
     * @return the balance of all transactions, as a double
     */
    public synchronized double rebuildBalanceFromTransactions() {
    	
    	double balance = 0.0; 
    	Date lastWithdrawalDt = null;
    	
        for (Transaction t: transactions) {
            if (t.getTransactionType() == Transaction.DEPOSIT) {
                balance += t.getTransactionAmount();
            } else {
                if (t.getTransactionType() == Transaction.WITHDRAWAL) {
                    balance -= t.getTransactionAmount();
                    lastWithdrawalDt = t.getTransactionDate();
                }
            }
        }
        lastWithdrawalDate = lastWithdrawalDt;
        
        return balance;       
    }
    

    /**
     * Check if transactions exist for this account.
     *
     * @return true if any transactions exist, in this account
     */
    public synchronized boolean checkIfTransactionsExist() {
       return (transactions.isEmpty() == false) ? true : false;
    }

}
