package com.abc;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    public static final BigDecimal NUM_OF_DAYS_YEAR = new BigDecimal("365");
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        //account Number
        String newAccountNumber="293220433";
        john.openAccount(new CheckingAccount(newAccountNumber));
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        String newAccountNumber="293220434";
        Account checkingAccount = new CheckingAccount(newAccountNumber);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);
        BigDecimal amount = new BigDecimal("1000.0");
        BigDecimal intresteExpected = (new BigDecimal("1")).divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);
        checkingAccount.deposit(amount);
        BigDecimal intreset = bank.totalInterestPaid();             
        assertTrue(intreset.compareTo(intresteExpected)==0);
         //assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    @Test
    public void savings_account() {
        Bank bank = new Bank();
        String newAccountNumber="293220489";
        Account savAccount = new SavingAccount(newAccountNumber);
        bank.addCustomer(new Customer("Bill").openAccount(savAccount));

        BigDecimal amount = new BigDecimal("1500.0");
        BigDecimal intresteExpected = new BigDecimal("2.0").divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);

        savAccount.deposit(amount);
        
        assertTrue(bank.totalInterestPaid().compareTo(intresteExpected)==0);

        //assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }


    @Test
    public void savings_account_test1() {
        Bank bank = new Bank();
        String newAccountNumber="293221189";
        Account savAccount = new SavingAccount(newAccountNumber);
        bank.addCustomer(new Customer("Tom").openAccount(savAccount));

        BigDecimal amount = new BigDecimal("1000.0");
        BigDecimal intresteExpected = new BigDecimal("1.0").divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);

        savAccount.deposit(amount);
        
        assertTrue(bank.totalInterestPaid().compareTo(intresteExpected)==0);

        //assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        String newAccountNumber="293220449";
        Account maxSavAccount = new MaxiSavingAccount(newAccountNumber);
        bank.addCustomer(new Customer("Bill").openAccount(maxSavAccount));

        BigDecimal amount = new BigDecimal("3000.0");
        BigDecimal intresteExpected = new BigDecimal("150.0").divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);

        maxSavAccount.deposit(amount);
        assertTrue(bank.totalInterestPaid().compareTo(intresteExpected)==0);

        //assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account_test1() {
        Bank bank = new Bank();
        String newAccountNumber="293220449";
        Account maxSavAccount = new MaxiSavingAccount(newAccountNumber);
        bank.addCustomer(new Customer("Bill").openAccount(maxSavAccount));

        BigDecimal amount = new BigDecimal("3000.0");
        BigDecimal intresteExpected = new BigDecimal("2.9").divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);

        maxSavAccount.deposit(amount);
        BigDecimal withdrawlAmount = new BigDecimal("100.0");
        try {
			maxSavAccount.withdraw(withdrawlAmount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        assertTrue(bank.totalInterestPaid().compareTo(intresteExpected)==0);

        //assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    

    @Test
    public void maxi_savings_account_test2() {
    	
    	class FakeDateProvider implements DateProviderInterface {
    		public Date thisDate;
    		
    	    public Date now() {
    	        return thisDate;
    	    }
    	}
    	
    	FakeDateProvider fakeDateProvider= new FakeDateProvider();
    	SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy");
    	Date date = null;
		try {
			date = sdf.parse("01-01-2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	fakeDateProvider.thisDate = date;
    	DateProvider.setInstance(fakeDateProvider);

    	
        Bank bank = new Bank();
        String newAccountNumber="293120449";
        Account maxSavAccount = new MaxiSavingAccount(newAccountNumber);
        bank.addCustomer(new Customer("Bill").openAccount(maxSavAccount));

        BigDecimal amount = new BigDecimal("3000.0");
        BigDecimal intresteExpected = new BigDecimal("145.0").divide(NUM_OF_DAYS_YEAR, MathContext.DECIMAL128);

        maxSavAccount.deposit(amount);
        BigDecimal withdrawlAmount = new BigDecimal("100.0");
         
        try {
			maxSavAccount.withdraw(withdrawlAmount);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
		try {
			date = sdf.parse("01-11-2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	fakeDateProvider.thisDate = date;

        assertTrue(bank.totalInterestPaid().compareTo(intresteExpected)==0);

        //assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
