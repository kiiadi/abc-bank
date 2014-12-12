package com.abc;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.builder.AccountBuilder;
import com.abc.controller.AccountController;
import com.abc.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountControllerTest {

	@Autowired
	final AccountController controller = null;
	
	@Test
	public void get() {
		final Account act1 = this.controller.add("Test Account", AccountBuilder.CHECKING);
		try {
			final Account act = this.controller.get(act1.getUid());
			assertNotNull(act);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}
	
	@Test
	public void add() {
		final Account act1 = this.controller.add("Test Account", AccountBuilder.SAVINGS);
		assertNotNull(act1);
	}
	
	@Test
	public void delete() {
		final Account act1 = this.controller.add("Test Account", AccountBuilder.CHECKING);
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
			this.controller.add("Test Account", AccountBuilder.CHECKING);
			
			final Collection<Account> acts = this.controller.getAll();
			
			assertTrue(acts.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
		
	}
	
	@Test
	public void contains() {
		try {
			final Account act = this.controller.add("Test Account", AccountBuilder.CHECKING);
			
			assertTrue(this.controller.contains(act.getUid()));
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}

}
