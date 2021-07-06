package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.database.Account;

public class AccountRepoImplTests {

	private static final Logger log = 
			Logger.getLogger(AccountRepoImplTests.class);
	private static AccountRepo ar;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		log.info("Set up new AccountRepo before class");
		ar = new AccountRepoImpl();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		log.info("Tear down AccountRepo after class");
		ar = null;
	}
	
	@Test
	public void getAccountSuccess() {
		log.info("Test getAccountSuccess");
		Account account1 = new Account(1,2,"Ntag","checking",-19.55);
		assertEquals(ar.getAccount(1).toString(), account1.toString());
	}
	
	@Test
	public void getAllAccountsSuccess() {
		log.info("Test getAllAccountsSuccess");
		assertTrue(ar.getAllAccounts().size() < 52
				&& ar.getAllAccounts().size() > 48);
	}
	
	@Test
	public void addAccountSuccess() {
		log.info("Test addAccountSuccess");
		Account testAccount = new Account(
				51, 9, "Test Savings", "savings", 49.23);
		Account addedAccount = ar.addAccount(testAccount);
		assertEquals(addedAccount.toString(), testAccount.toString());
	}
	
	@Test
	public void updateAccountSuccess() {
		log.info("Test updateAccountSuccess");
		Account testAccount = new Account(40, 5, "Test CD", "CD", 1005.97);
		Account beforeUpdateAccount = ar.getAccount(40);
		Account updatedAccount = ar.updateAccount(testAccount);
		assertNotEquals(beforeUpdateAccount.toString(), updatedAccount.toString());
		assertEquals(updatedAccount.toString(), testAccount.toString());
	}
	
	@Test
	public void deleteAccountSuccess() {
		log.info("Test deleteAccountSuccess");
		Account beforeDeleteAccount = ar.getAccount(30);
		assertEquals(ar.deleteAccount(30).toString(),
				beforeDeleteAccount.toString());
		assertNull(ar.getAccount(30));
	}
	
	@Test
	public void withdrawSuccess() {
		log.info("Test withdrawSuccess");
		double beforeWithdraw = ar.getAccount(26).getBalance();
		double amount = 2000.50;
		double afterWithdraw = ar.withdraw(26, amount).getBalance();
		assertTrue(beforeWithdraw == 2303.17);
		assertTrue(afterWithdraw == 302.67);
		
	}
	
	@Test
	public void depositSuccess() {
		log.info("Test depositSuccess");
		double beforeDeposit = ar.getAccount(35).getBalance();
		double amount = 1234.56;
		double afterDeposit = ar.deposit(35, amount).getBalance();
		assertTrue(beforeDeposit == 416.57);
		assertTrue(afterDeposit == 1651.13);
	}

}
