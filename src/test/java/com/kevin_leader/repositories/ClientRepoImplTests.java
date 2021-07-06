package com.kevin_leader.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kevin_leader.models.database.Client;

public class ClientRepoImplTests {
	
	private static final Logger log = 
			Logger.getLogger(ClientRepoImplTests.class);
	private static ClientRepo cr;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		log.info("Set up new ClientRepo before class");
		cr = new ClientRepoImpl();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		log.info("Tear down ClientRepo after class");
		cr = null;
	}
	
	@Test
	public void getClientSuccess() {
		log.info("Test getClientSuccess");
		Client client1 = new Client(1, "Henriette", "Preedy",
				"hpreedy0@t.co", "ayWP97");
		assertEquals(cr.getClient(1).toString(), client1.toString());
	}
	
	@Test
	public void getAllClientsSuccess() {
		log.info("Test getAllClientsSuccess");
		assertTrue(cr.getAllClients().size() < 22
				&& cr.getAllClients().size() > 18);
	}
	
	@Test
	public void addClientSuccess() {
		log.info("Test addClientSuccess");
		Client testClient = new Client(
				21, "Ron", "Swanson","abc@123.xyz", "abc123");
		Client addedClient = cr.addClient(testClient);
		assertEquals(addedClient.toString(), testClient.toString());
	}
	
	@Test
	public void updateClientSuccess() {
		log.info("Test updateClientSuccess");
		Client testClient = new Client(
				11, "Ron", "Swanson","abc@123.xyz", "abc123");
		Client beforeUpdateClient = cr.getClient(11);
		Client updatedClient = cr.updateClient(testClient);
		assertNotEquals(beforeUpdateClient.toString(),
				updatedClient.toString());
		assertEquals(updatedClient.toString(), testClient.toString());
	}
	
	@Test
	public void deleteClientSuccess() {
		log.info("Test deleteClientSuccess");
		Client beforeDeleteClient = cr.getClient(19);
		assertEquals(cr.deleteClient(19).toString(),
				beforeDeleteClient.toString());
		assertNull(cr.getClient(19));
	}
	
}
