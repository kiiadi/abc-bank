package com.abc;

import static org.junit.Assert.assertEquals;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.abc.customer.ICustomer;
import com.abc.customer.IndividualCustomer;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestBank {
	
	private Bank bank;
	
	@Injectable
	private ICustomer mockCustomer1;
	
	@Injectable
	private ICustomer mockCustomer2;
	
	@Before
	public void setUp(){
		bank = new Bank();
	}
	
	@Test
	public void shouldInitializeBankWithZeroCustomer(){
		assertEquals(0, bank.getCustomerCount());
	}
	
	@Test
	public void shouldAddCustomers(){
		ICustomer customer1 = new IndividualCustomer("C1");
		ICustomer customer2 = new IndividualCustomer("C1");
		bank.addCustomer(customer1);
		bank.addCustomer(customer2);
		assertEquals(2, bank.getCustomerCount());
	}
	
	@Test
	public void shouldGetStatement(){
		new NonStrictExpectations() {{
			mockCustomer1.getStatement();
			returns("Customer ID: C1 - Customer Name: lastName, firstName - Number of Account(s): 2");
			mockCustomer2.getStatement();
			returns("Customer ID: C2 - Customer Name: Thomas, Sanju - Number of Account(s): 1");
		}};
		
		bank.addCustomer(mockCustomer1);
		bank.addCustomer(mockCustomer2);
		final String customerSummary = bank.getCustomerSummary();
		assertEquals("Customer ID: C1 - Customer Name: lastName, firstName - Number of Account(s): 2\n"
				+ "Customer ID: C2 - Customer Name: Thomas, Sanju - Number of Account(s): 1\n", customerSummary);
		
		new Verifications() {{
			mockCustomer1.getStatement();
			mockCustomer2.getStatement();
		}};
	}
	
	@Test
	public void shouldGetTotalInterestPaid(){
		new NonStrictExpectations() {{
			mockCustomer1.getTotalInterestEarned();
			returns(100.00);
			mockCustomer2.getTotalInterestEarned();
			returns(100.00);
		}};
		
		bank.addCustomer(mockCustomer1);
		bank.addCustomer(mockCustomer2);
		assertEquals(200.00, bank.getTotalInterestPaid(), 0);
		
		new Verifications() {{
			mockCustomer1.getTotalInterestEarned();
			mockCustomer2.getTotalInterestEarned();
		}};
	}
	
}
