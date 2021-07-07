package com.kevin_leader.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.database.Account;
import com.kevin_leader.models.database.Client;
import com.kevin_leader.repositories.AccountRepo;
import com.kevin_leader.repositories.AccountRepoImpl;
import com.kevin_leader.repositories.ClientRepo;
import com.kevin_leader.repositories.ClientRepoImpl;

public class BankingServiceImplTests {
	
	private static final Logger log = 
			Logger.getLogger(BankingServiceImplTests.class);
	private static BankingService bs;
	private static ClientRepo cr;
	private static AccountRepo ar;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		log.info("Set up new BankingService before class");
		cr = new ClientRepoImpl();
		ar = new AccountRepoImpl();
		bs = new BankingServiceImpl(cr, ar);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		log.info("Tear down BankingService after class");
		bs = null;
		cr = null;
		ar = null;
	}
	
	@Test
	public void getClientSuccess() {
		log.info("Test getClientSuccess");
		Client client1 = new Client(1, "Henriette", "Preedy",
				"hpreedy0@t.co", "ayWP97");
		assertEquals(bs.getClient(1).toString(), client1.toString());
	}
	
	@Test
	public void getAllClientsSuccess() {
		log.info("Test getAllClientsSuccess");
		assertTrue(bs.getAllClients().size() < 22
				&& bs.getAllClients().size() > 18);
	}
	
	@Test
	public void addClientSuccess() {
		log.info("Test addClientSuccess");
		Client testClient = new Client(
				"Ron", "Swanson","abc@123.xyz", "abc123");
		Client addedClient = bs.addClient(testClient);
		assertTrue(addedClient != null);
		assertEquals(addedClient.getFirstName(), "Ron");
		assertEquals(addedClient.getLastName(), "Swanson");
		assertEquals(addedClient.getEmail(), "abc@123.xyz");
		assertEquals(addedClient.getPassword(), "abc123");
	}
	
	@Test
	public void updateClientSuccess() {
		log.info("Test updateClientSuccess");
		Client testClient = new Client(
				16, "OK", "Saloon","abc@123.xyz", "abc123");
		Client beforeUpdateClient = bs.getClient(16);
		Client updatedClient = bs.updateClient(testClient);
		assertNotEquals(beforeUpdateClient.toString(),
				updatedClient.toString());
		assertEquals(updatedClient.toString(), testClient.toString());
	}
	
	@Test
	public void deleteClientSuccess() {
		log.info("Test deleteClientSuccess");
		Client beforeDeleteClient = bs.getClient(5);
		assertEquals(bs.deleteClient(5).toString(),
				beforeDeleteClient.toString());
		assertNull(bs.getClient(5));
	}
	
	@Test
	public void withdrawSuccess() {
		log.info("Test withdrawSuccess");
		double beforeWithdraw = bs.getAccountForClient(12, 46).getBalance();
		double amount = 40.40;
		double afterWithdraw = bs.withdraw(46, amount).getBalance();
		assertTrue(beforeWithdraw == 44.02);
		assertTrue(afterWithdraw == 3.62);
	}
	
	@Test
	public void depositSuccess() {
		log.info("Test depositSuccess");
		double beforeDeposit = bs.getAccountForClient(9, 47).getBalance();
		double amount = 543.21;
		double afterDeposit = bs.deposit(47, amount).getBalance();
		assertTrue(beforeDeposit == 2454.68);
		assertTrue(afterDeposit == 2997.89);
	}
	
	@Test
	public void getAllAccountsForClientSuccess() {
		log.info("Test getAllAccountsForClient");
		List<Account> accounts = bs.getAllAccountsForClient(7);
		assertTrue(accounts.size() == 5);
		List<Account> fakeIdAccounts = bs.getAllAccountsForClient(229);
		assertTrue(fakeIdAccounts.size() == 0);
	}
	
	@Test
	public void getAllAccountsForClientBetweenBalancesSuccess() {
		log.info("Test getAllAccountsForClientBetweenBalancesSuccess");
		List<Account> badIdAccounts = 
				bs.getAllAccountsForClientBetweenBalances(-1, -2, -3);
		assertEquals(badIdAccounts.size(), 0);
		List<Account> goodParamsAccounts =
				bs.getAllAccountsForClientBetweenBalances(7, 400, 2000);
		assertEquals(goodParamsAccounts.size(), 2);
	}
	
	@Test
	public void getAccountForClientSuccess() {
		log.info("Test getAccountForClientSuccess");
		Account expectedAccount = new Account(
				34, 14, "Jaxspan", "savings", 969.99);
		Account retreivedAccount = bs.getAccountForClient(14, 34);
		Account wrongClientIdAccount = bs.getAccountForClient(1, 34);
		assertEquals(expectedAccount.toString(), retreivedAccount.toString());
		assertNull(wrongClientIdAccount);
	}
	
	@Test
	public void addAccountForClientSuccess() {
		log.info("Test addAccountForClientSuccess");
		Account accountToAdd = new Account(
				9, "Trogdor Burninating", "burninating", 666.99);
		Account addedAccount = bs.addAccountForClient(accountToAdd);
		assertTrue(addedAccount != null);
		assertEquals(addedAccount.getClientId(), 9);
		assertEquals(addedAccount.getAccountName(), "Trogdor Burninating");
		assertEquals(addedAccount.getAccountType(), "burninating");
		assertTrue(addedAccount.getBalance() == 666.99);
		
		Account badClientIdAccount = new Account(
				42, "Trogdor", "burninating", 666.99);
		Account badAddAccount = bs.addAccountForClient(badClientIdAccount);
		assertNull(badAddAccount);
	}
	
	@Test
	public void updateAccountForClientSuccess() {
		log.info("Test updateAccountForClientSuccess");
		Account accountToUpdateTo = new Account(
				29, 14, "idk", "angsting", 0.01);
		Account updatedAccount = bs.updateAccountForClient(accountToUpdateTo);
		assertEquals(updatedAccount.toString(), accountToUpdateTo.toString());
		
		Account badClientIdAccount = new Account(
				29, 42, "idk", "angsting", 0.01);
		Account badUpdateAccount = 
				bs.updateAccountForClient(badClientIdAccount);
		assertNull(badUpdateAccount);
	}
	
	@Test
	public void deleteAccountForClientSuccess() {
		log.info("Test deleteAccountForClientSuccess");
		Account accountToDelete = bs.getAccountForClient(6, 21);
		Account deletedAccount = bs.deleteAccountForClient(6, 21);
		Account missingAccount = bs.getAccountForClient(6, 21);
		assertNull(missingAccount);
		assertEquals(accountToDelete.toString(), deletedAccount.toString());
	}
}
