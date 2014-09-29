package com.abc.customer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestIndividualInformation {

	private IndividualInformation customerInformation;
	
	@Before
	public void setUp(){
		customerInformation = new IndividualInformation("firstName", "lastName");
	}
	
	@Test
	public void shouldReturnFirstName(){
		assertEquals("firstName", customerInformation.getFirstName());
	}
	
	@Test
	public void shouldReturnLastName(){
		assertEquals("lastName", customerInformation.getLastName());
	}
	
}
