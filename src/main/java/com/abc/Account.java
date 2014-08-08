package com.abc;

import static java.lang.Math.abs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * The enumerator represents possible account types.
 */
enum AccountType {
	CHECKING,
	SAVINGS,
	MAXI_SAVINGS
}


/**
 * The enumerator represents current interest rates.
 */
enum InterestRates {
	CHECKING_RATE(0.001),
	SAVING_RATE(0.002),
	MAXI_SAVING_RATE(0.05);
	
	private double val;
	
	InterestRates(double val) {
        this.val = val;
    }
	
	public double getVal() {
        return val;
    }
	
}


/**
 * The class implements basic manipulations with a bank account and
 * encapsulates all transaction related logic.
 */
public class Account {
    private final AccountType accountType;
    private List<Transaction> transactions;
    private double checkingRate;
    private double savingRateMax;
    private double maxSavingRateMax;
    private Date lastWithdrawDate;

    /**
     * Create Account instance.
     */
    public Account(AccountType accountType) {
        this.accountType = accountType;
        transactions = new ArrayList<Transaction>();
        // Initialize daily rates
        checkingRate = InterestRates.CHECKING_RATE.getVal() / 365;
        savingRateMax = InterestRates.SAVING_RATE.getVal() / 365;
        maxSavingRateMax = InterestRates.MAXI_SAVING_RATE.getVal() / 365;
    }

    /**
     * Add amount to account on current date.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }
    
    /**
     * Add amount to account on given date
     */
    public void deposit(double amount, Date date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, date));
        }
    }

    /**
     * Withdraw amount from account on current date.
     */
    public void withdraw(double amount) {
    	if (amount <= 0) {
    		throw new IllegalArgumentException("Amount must be greater than zero");
    	} else {
    		double balance = sumTransactions();
    		if (balance < amount)
    			throw new IllegalArgumentException("Account balance is not allowed to overdraw");
    		
    		Transaction t = new Transaction(-amount);
    		transactions.add(t);
    		lastWithdrawDate = t.getDate();
    	}
    }
    
    /**
     * Withdraw amount from account on given date.
     */
    public void withdraw(double amount, Date date) {
    	if (amount <= 0) {
    		throw new IllegalArgumentException("Amount must be greater than zero");
    	} else {
    		double balance = sumTransactions();
    		if (balance < amount)
    			throw new IllegalArgumentException("Account balance is not allowed to overdraw ");
    		
    		Transaction t = new Transaction(-amount, date);
    		transactions.add(t);
    		lastWithdrawDate = t.getDate();
    	}
    }
    
    /*
     * Checking accounts have a flat rate of 0.1%.
     * Savings accounts have a rate of 0.1% for the first $1,000 then 0.2%.
     * Maxi-Savings accounts have a rate of 2% for the first $1,000 then 5% for the next $1,000 then 10%.
     * 
     */
    private double calculateEarned(double amount, Date startDate, Date calcDate) {
    	int days = dateDiff(startDate, calcDate);
    	switch(accountType){
        	case SAVINGS:
        		if (amount <= 1000)
        			return amount * days * checkingRate;
        		else {
        			double below = 1000 * days * checkingRate;
        			double above = (amount-1000) * days * savingRateMax;
        			return below + above;
        		}
        	case MAXI_SAVINGS:
        		if (lastWithdrawDate != null) {
        			if (lastWithdrawDate.before(calcDate)) {
        				int diff = dateDiff(lastWithdrawDate, calcDate);
        				if (diff > 10)
        					return amount * days * maxSavingRateMax;
        				else
        					return amount * days * checkingRate;
        			}
        		}

        		if (days > 10 || lastWithdrawDate == null) 
        			return amount * days * maxSavingRateMax;
        		else
        			return amount * days * checkingRate;
        	default:
        		return amount * days * checkingRate;
    	}
    }    
    
    /**
     * Calculate earned interest until current date.<br>
     * See {@link  #interestEarned(Date)}
     */
    public double interestEarned() {
    	return interestEarned(Calendar.getInstance().getTime());
    }
    
    /**
     * Calculate earned interest until given date.<br>
     * Interest calculation depends on account type, account balance and applied daily interest rates.
     */
    public double interestEarned(Date date) {
    	if (transactions.size() == 0)
    		return 0.0;
    	
    	// Check if there were any withdraws during past 10 days.
    	if (lastWithdrawDate != null) {
    		int diff = dateDiff(lastWithdrawDate, date);
    		if (diff > 10) 
    			lastWithdrawDate = null;
    	}
    	
    	// If there is only 1 deposit transaction calculate interest
    	// from date of the transaction until now.
    	if (transactions.size() == 1) {
    		Transaction t = transactions.get(0);
    		return calculateEarned(t.getAmount(), t.getDate(), date);
    	} 
    	
    	// Otherwise we need to calculate interest for each transaction separately
    	// depending on its length in days. 
    	double balance = 0.0;
    	List<Double> amounts = new ArrayList<Double>(); 
    	for (int i=1; i<transactions.size(); i++) {
    		Transaction curr = transactions.get(i-1);
    		Transaction next = transactions.get(i);
    		balance += curr.getAmount();
    		amounts.add(calculateEarned(balance, curr.getDate(), next.getDate()));    		
    	}
    	// Adjust calculation for the last transaction
    	Transaction last = transactions.get(transactions.size()-1);
    	balance += last.getAmount();
    	amounts.add(calculateEarned(balance, last.getDate(), date)); 
    	
    	double sum = 0.0;
    	for(Double e : amounts)
    		sum += e;
    	
    	return sum;
    }

    /**
     * Calculate total account balance.
     */
    public double sumTransactions() {
    	double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }

    /**
     * Return account type.
     */
    public AccountType getAccountType() {
        return accountType;
    }
    
    /**
     * Return account transactions.
     */
    public List<Transaction> getTransactions() {
    	return transactions;
    }
    
    /**
     * Return number of transactions for the account.
     */
    public int numberOfTransactions() {
    	return transactions.size();
    }
    
    //assert: startDate must be before endDate  
    private int dateDiff(Date start, Date end) {
    	// Calculate days between 2 dates
    	// including daylight saving adjustment
    	int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
    	Calendar calendar = Calendar.getInstance();
    	TimeZone tz = calendar.getTimeZone();
    	int dst = calendar.get(Calendar.DST_OFFSET);  
    	long duration = end.getTime() - start.getTime();
    	if (tz.inDaylightTime(start) && !tz.inDaylightTime(end))
    		duration -= dst;
    	else if (!tz.inDaylightTime(start) && tz.inDaylightTime(end))
    		duration += dst;
    	return (int) (duration / MILLIS_IN_DAY);
    }
    
    private String statementForAccount() {
    	// Translate to pretty account type
    	switch(accountType){
    		case SAVINGS:
    			return "Saving Account";
    		case MAXI_SAVINGS:
    			return "Maxi Saving Account";
    		default:
    			return "Checking Account";
    	}
    }
    
    /**
     * Return pretty representation of account info.
     */
    public String getStatement() {
    	StringBuilder sb = new StringBuilder(toString());
    	//sb.append("\n");
    	// Now total up all the transactions
        double total = 0.0;
    	for (Transaction t : transactions) {
    		double amount = t.getAmount();
    		sb.append("  ");
    		sb.append(t.toString());
            sb.append("\n");
            total += amount;
    	}
    	sb.append("Total ");
        sb.append(toDollars(total));
        sb.append("\n");
    	return sb.toString();
    }
    	
    @Override
    public String toString() {
    	return String.format("%s (%s)\n", 
    						statementForAccount(), 
    						formatNumber(numberOfTransactions(), "transaction"));
    }
    
    /*****************************************************************************/
    /*** Useful utility methods.They can be moved to a separate utility class. ***/
    /*****************************************************************************/
    
    /**
     * Return correct plural of word is created based on given number:
     * If number passed in is 1 just return the word otherwise add an 's' at the end
     */
    public static String formatNumber(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
    
    /**
     * Return string representation of dolar amount.
     */
    public static String toDollars(double amount){
        return String.format("$%,.2f", abs(amount));
    }
    
    /**
	 * Return date from given formated string.
	 */
	public static Date getDateFromString(String str) {
		try {
			return new SimpleDateFormat("MMMM d, yyyy", Locale.US).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * Return string from given date.
	 */
	public static String getStringFromDateFromDate(Date date) {
		return new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(date);
	}

}
