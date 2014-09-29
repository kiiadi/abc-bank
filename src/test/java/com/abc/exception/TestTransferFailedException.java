package com.abc.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestTransferFailedException {
	
	private ValidationException transferFailedException;

	@Before
	public void setUp(){
		transferFailedException = new TransferFailedException("Failed due to unexpected error!, please contact customer support");
	}
	
	@Test
	public void shouldGetMessage(){
		assertEquals("Failed due to unexpected error!, please contact customer support", transferFailedException.getMessage());
	}
}
