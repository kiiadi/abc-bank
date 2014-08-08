package com.abc;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for {@link Transaction}
 */
public class TransactionTest {	
    
	/**
	 * Test transaction creating.
	 */
	@Test
    public void transactionCreation() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
        assertTrue(t.getAmount() == 5 );
        
        Date d = Account.getDateFromString("February 1, 2014");
        t = new Transaction(50.0, d);
        assertTrue(t.getAmount() == 50.0 );
        assertTrue(d.equals(t.getDate()));
    }
}
