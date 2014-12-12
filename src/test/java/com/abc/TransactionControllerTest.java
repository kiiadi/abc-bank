package com.abc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.builder.TransactionBuilder;
import com.abc.controller.TransactionController;
import com.abc.model.Customer;
import com.abc.model.Transaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionControllerTest {

	@Autowired
	final TransactionController controller = null;
	
	@Test
	public void get() {
		final Transaction act1 = this.controller.add(100.00, TransactionBuilder.DEPOSIT);
		try {
			final Transaction act = this.controller.get(act1.getUid());
			assertNotNull(act);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}
	
	@Test
	public void add() {
		final Transaction act1 = this.controller.add(100.00, TransactionBuilder.WITHDRAW);
		assertNotNull(act1);
	}
	
	@Test
	public void delete() {
		final Transaction act1 = this.controller.add(1000.00, TransactionBuilder.DEPOSIT);
		
		try {
			this.controller.delete(act1.getUid());
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}
	
	@Test
	public void getAll() {
		try {
			this.controller.add(100.00, TransactionBuilder.INTEREST);
			
			
			final Collection<Transaction> acts = this.controller.getAll();
			
			assertTrue(acts.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
		
	}
	
	@Test
	public void contains() {
		try {
			final Transaction act1 = this.controller.add(100.00, TransactionBuilder.DEPOSIT);
			
			assertTrue(this.controller.contains(act1.getUid()));
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}

}
